package cn.seecoder.courselearning.service.post;

import cn.seecoder.courselearning.mapperservice.post.CoursePostCommentsMapper;
import cn.seecoder.courselearning.po.post.CoursePostComment;
import cn.seecoder.courselearning.util.Constant;
import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.post.CoursePostCommentVO;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CoursePostCommentTest {
    @Resource
    PostService postService;
    @Resource
    CoursePostCommentsMapper coursePostCommentsMapper;


    static CoursePostCommentVO testData_1 =
            new CoursePostCommentVO(0,0,"content","0",0,"0");
    static ResultVO<CoursePostCommentVO> testData_2 =
            new ResultVO<>(Constant.REQUEST_SUCCESS,"已成功发表评论！",testData_1);

    //创建一个正确对象，调用接口创建一个测试对象，然后进行断言比较
    @Test
    public void getCoursePostCommentsTest(){
        List<CoursePostCommentVO> testRes = postService.getCoursePostComments(1,0,"0");
        List<CoursePostComment> coursePostComments = coursePostCommentsMapper.get(1,0,"0");
        List<CoursePostCommentVO> cpcVO = new ArrayList<>();
        for (CoursePostComment cpc: coursePostComments){
            cpcVO.add(new CoursePostCommentVO(cpc.postuid,cpc.courseId,cpc.content,cpc.posttime,cpc.commentuid,cpc.commenttime));
        }
        for (int i = 0; i<(Math.min(cpcVO.size(), testRes.size())); i++){
            Assert.assertEquals(cpcVO.get(i).posttime,testRes.get(i).posttime);
            Assert.assertEquals(cpcVO.get(i).content,testRes.get(i).content);
            Assert.assertEquals(cpcVO.get(i).postuid,testRes.get(i).postuid);
            Assert.assertEquals(cpcVO.get(i).courseId,testRes.get(i).courseId);
            Assert.assertEquals(cpcVO.get(i).commentuid,testRes.get(i).commentuid);
            Assert.assertEquals(cpcVO.get(i).commenttime,testRes.get(i).commenttime);
        }
    }

    @Test
    public void addCommentTest(){

        ResultVO<CoursePostCommentVO> res =
                postService.addCoursePostComment(0,0,"content","0",0,"0");
        Assert.assertEquals(testData_2.getData().commenttime,res.getData().commenttime);
        Assert.assertEquals(testData_2.getData().commentuid,res.getData().commentuid);
        Assert.assertEquals(testData_2.getData().courseId,res.getData().courseId);
        Assert.assertEquals(testData_2.getData().postuid,res.getData().postuid);
        Assert.assertEquals(testData_2.getData().content,res.getData().content);
        Assert.assertEquals(testData_2.getData().posttime,res.getData().posttime);


    }



}
