package cn.seecoder.courselearning.service;

import cn.seecoder.courselearning.vo.CourseVO;
import cn.seecoder.courselearning.vo.ResultVO;
import com.github.pagehelper.PageInfo;

import java.util.List;


public interface CourseService {
    // 根据关键字，分页查询课程
    PageInfo<CourseVO> getCourses(Integer currPage, Integer pageSize, Integer uid, String key);
    // 根据分类，分页查询课程
    PageInfo<CourseVO> getCoursesByType(Integer currPage, Integer pageSize, Integer uid, String type);
    // 查询用户所有已购买的课程
    List<CourseVO> getBoughtCourses(Integer uid);
    // 查询教师所有可管理的课程
    List<CourseVO> getManageableCourses(Integer uid);
    // 查询单门课程
    CourseVO getCourse(Integer courseId, Integer uid);
    // 创建课程
    ResultVO<CourseVO> createCourse(CourseVO courseVO);
}