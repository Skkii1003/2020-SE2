package cn.seecoder.courselearning.service.post;

import cn.seecoder.courselearning.mapperservice.post.CoursePostsMapper;
import cn.seecoder.courselearning.po.post.CoursePost;
import cn.seecoder.courselearning.util.Constant;
import cn.seecoder.courselearning.vo.post.CoursePostVO;
import cn.seecoder.courselearning.vo.ResultVO;
import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CoursePostTest{
    @Resource
    PostService postService;
    @Resource
    CoursePostsMapper coursePostsMapper;

    static CoursePostVO testData_1 = new CoursePostVO(0,0,"0","content","0");

    static ResultVO<CoursePostVO> testData_2 = new ResultVO<>(Constant.REQUEST_SUCCESS, "已成功评价！", testData_1);

    //创建一个正确对象，调用接口创建一个测试对象，然后进行断言比较
    @Test
    public void addPostTest(){
        System.out.println("进入");
//        ResultVO<CoursePostVO> testRes =
//                new ResultVO<>(Constant.REQUEST_SUCCESS, "已成功评价!", new CoursePostVO(0,0,"0","content","0"));
//        System.out.println(testData_1.cid+" "+testData_1.uid+" "+testData_1.title+" "+testData_1.comment+" "+testData_1.time);
        ResultVO<CoursePostVO> testRes = postService.addCoursePost(0, 0, "0", "content", "0");
        System.out.println("出来");
        Assert.assertEquals(testData_2.getCode(),testRes.getCode());
        Assert.assertEquals(testData_2.getMsg(),testRes.getMsg());
        Assert.assertEquals(testData_2.getData().cid,testRes.getData().cid);
        Assert.assertEquals(testData_2.getData().uid,testRes.getData().uid);
        Assert.assertEquals(testData_2.getData().comment,testRes.getData().comment);
        Assert.assertEquals(testData_2.getData().last_ctime,testRes.getData().last_ctime);
        Assert.assertEquals(testData_2.getData().time,testRes.getData().time);
        Assert.assertEquals(testData_2.getData().title,testRes.getData().title);
    }

    @Test
    public void getPostTest(){
        List<CoursePostVO> testRes = postService.getCoursePosts(0);
        List<CoursePost> coursePosts = coursePostsMapper.get(0);
        List<CoursePostVO> cpVO = new ArrayList<>();
        for (CoursePost cp: coursePosts){
            cpVO.add(new CoursePostVO(cp.courseId,cp.uid,cp.title,cp.content,cp.ctime));
        }
        Assert.assertArrayEquals("getPostTestFail",testRes.toArray(),cpVO.toArray());
    }

}