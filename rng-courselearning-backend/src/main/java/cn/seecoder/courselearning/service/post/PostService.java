package cn.seecoder.courselearning.service.post;

import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.post.CoursePostCommentVO;
import cn.seecoder.courselearning.vo.post.CoursePostVO;
import cn.seecoder.courselearning.vo.post.PostNoticeVO;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

public interface PostService {
    //发帖
    ResultVO<CoursePostVO> addCoursePost(Integer courseId, Integer uid, String title, String comment, String time);
    //根据课程号，获得相关帖子
    List<CoursePostVO> getCoursePosts(Integer courseId);
    //对一个帖子发表回复
    ResultVO<CoursePostCommentVO> addCoursePostComment(Integer postuid, Integer courseId, String content, String posttime, Integer commentuid, String commenttime);
    //根据帖子的主键，获得帖子下的回复
    List<CoursePostCommentVO> getCoursePostComments(Integer courseId,Integer postuid,String posttime);
    //添加通知
    ResultVO<PostNoticeVO> addNotice( Integer senderUid,
                                         Integer receiverUid,
                                         String sendTime,
                                         String url);
    //通过接受者id得到通知
    List<PostNoticeVO> getNotice(Integer receiverUid);
    //检查通知
    PostNoticeVO checkNotice(String sendTime);
}
