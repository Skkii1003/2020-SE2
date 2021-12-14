package cn.seecoder.courselearning.serviceimpl.course;

import cn.seecoder.courselearning.mapperservice.course.CourseLikesMapper;
import cn.seecoder.courselearning.mapperservice.course.CourseMapper;
import cn.seecoder.courselearning.po.course.Course;
import cn.seecoder.courselearning.po.order.CourseOrder;
import cn.seecoder.courselearning.service.course.CourseService;
import cn.seecoder.courselearning.service.order.QueryOrderService;
import cn.seecoder.courselearning.util.Constant;
import cn.seecoder.courselearning.util.PageInfoUtil;
import cn.seecoder.courselearning.vo.course.CourseVO;
import cn.seecoder.courselearning.vo.ResultVO;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {
    @Resource
    private CourseMapper courseMapper;
    @Resource
    private CourseLikesMapper courseLikesMapper;

    private QueryOrderService orderService;

    @Autowired
    public void setOrderService(QueryOrderService orderService) {
        this.orderService = orderService;
    }

    @Override
    public PageInfo<CourseVO> getCourses(Integer currPage, Integer pageSize, Integer uid, String key) {
        if(currPage==null || currPage<1) currPage=1;
        PageHelper.startPage(currPage, pageSize);
        PageInfo<Course> po = new PageInfo<>(courseMapper.queryAll(key));
        return getCourseVOPageInfo(uid, po);
    }

    @Override
    public PageInfo<CourseVO> getCoursesByType(Integer currPage, Integer pageSize, Integer uid, String type) {
        if(currPage==null || currPage<1) currPage=1;
        PageHelper.startPage(currPage, pageSize);
        PageInfo<Course> po = new PageInfo<>(courseMapper.selectByType(type));
        return getCourseVOPageInfo(uid, po);
    }

    @Override
    public PageInfo<CourseVO> getHotCourses(Integer currPage, Integer pageSize, Integer uid) {
        if(currPage==null || currPage<1) currPage=1;
        PageHelper.startPage(currPage, pageSize);
        PageInfo<Course> po = new PageInfo<>(courseMapper.selectHotCourses());
        return getCourseVOPageInfo(uid, po);
    }

    @Override
    public List<CourseVO> getBoughtCourses(Integer uid) {

        List<CourseVO> ret = new ArrayList<>();
        List<Course> courseList = courseMapper.selectByStudentId(uid);
        for(Course course: courseList){
            int like=courseLikesMapper.count(course.getId(),uid);
            boolean liked= like > 0;
            ret.add(new CourseVO(course, true, false,liked));
        }
        return ret;
    }

    @Override
    public List<CourseVO> getManageableCourses(Integer uid) {
        List<CourseVO> ret = new ArrayList<>();
        List<Course> courseList = courseMapper.selectByTeacherId(uid);
        for(Course course: courseList){
            int like=courseLikesMapper.count(course.getId(),uid);
            boolean liked= like > 0;
            ret.add(new CourseVO(course, false, true,liked));
        }
        return ret;
    }
    @Override
    public CourseVO getCourse(Integer courseId, Integer uid) {
        Course course = courseMapper.selectByPrimaryKey(courseId);
        course.setLikes(courseLikesMapper.countLikesOfCourse(course.getId()));
        boolean bought = false;
        boolean manageable = false;
        if(uid != null && uid > 0) {
            CourseOrder order = orderService.queryMostRecentOrder(uid, courseId);
            if(order != null)
                bought = order.getStatus().equals(Constant.ORDER_STATUS_SUCCESS);
            manageable = uid.equals(course.getTeacherId());
        }
        int like=courseLikesMapper.count(course.getId(),uid);
        boolean liked= like > 0;
        System.out.println(courseId+":"+uid+"点赞状态："+liked);
        return new CourseVO(course, bought, manageable,liked);
    }

    @Override
    public ResultVO<CourseVO> createCourse(CourseVO courseVO) {
        courseVO.setCreateTime(new Date());
        for(Course course: courseMapper.selectByTeacherId(courseVO.getTeacherId())){
            if (course.getName().equals(courseVO.getName()))
                return new ResultVO<>(Constant.REQUEST_FAIL, "已存在同名课程！");
        }
        Course course = new Course(courseVO);
        course.setLikes(courseLikesMapper.countLikesOfCourse(course.getId()));
        System.out.println("调用CourseMapper.countLikesOfCourse");
        if(courseMapper.insert(course) > 0){
            return new ResultVO<>(Constant.REQUEST_SUCCESS, "课程创建成功。", new CourseVO(course, false, true,false));
        }
        return new ResultVO<>(Constant.REQUEST_FAIL, "服务器错误");
    }


    @Override
    public Course getByPrimaryKey(Integer courseId) {
        return courseMapper.selectByPrimaryKey(courseId);
    }

    @Override
    public ResultVO<CourseVO> getCourseLike(Integer uid, Integer courseId) {
        int likeNum=courseLikesMapper.count(courseId,uid);
        CourseVO courseVO=getCourse(courseId,uid);
        if (likeNum>0)
            return new ResultVO<>(Constant.REQUEST_SUCCESS,"已经点过赞了",courseVO);
        return new ResultVO<>(Constant.REQUEST_FAIL,"还没点过赞",courseVO);
    }

    @Override
    public ResultVO<CourseVO> addCourseLike(Integer uid, Integer courseId) {
        courseLikesMapper.insert(courseId,uid);

        CourseVO courseVO=getCourse(courseId,uid);
        return new ResultVO<>(Constant.REQUEST_SUCCESS,"已点赞！",courseVO);
    }

    @Override
    public ResultVO<CourseVO> cancelCourseLike(Integer uid, Integer courseId) {
        courseLikesMapper.deleteByPrimaryKey(courseId,uid);
        CourseVO courseVO=getCourse(courseId,uid);
        return new ResultVO<>(Constant.REQUEST_SUCCESS,"已取消点赞！",courseVO);
    }


    private PageInfo<CourseVO> getCourseVOPageInfo(Integer uid, PageInfo<Course> po) {
        PageInfo<CourseVO> result = PageInfoUtil.convert(po, CourseVO.class);
        if(uid != null && uid > 0){
            List<CourseVO> voList = result.getList();
            for(CourseVO vo: voList){
                CourseOrder order = orderService.queryMostRecentOrder(uid, vo.getId());
                if(order != null)
                    vo.setBought(order.getStatus().equals(Constant.ORDER_STATUS_SUCCESS));
                vo.setManageable(uid.equals(vo.getTeacherId()));
            }
        }
        return result;
    }
}
