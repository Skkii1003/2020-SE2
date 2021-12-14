package cn.seecoder.courselearning.controller.post;

import cn.seecoder.courselearning.po.post.PostNotice;

import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.post.CoursePostCommentVO;
import cn.seecoder.courselearning.vo.post.CoursePostVO;
import cn.seecoder.courselearning.vo.post.PostNoticeVO;
import org.springframework.web.bind.annotation.*;
import cn.seecoder.courselearning.service.post.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class PostController {
    @Resource
    private PostService postService;
    @PostMapping("/add_post")
    public ResultVO<CoursePostVO> addCoursePost(@RequestParam Integer courseId, @RequestParam Integer uid, @RequestParam String title, @RequestParam String content, @RequestParam String time){
        System.out.println("CourseController: "+title);
        return postService.addCoursePost(courseId,uid,title,content,time);
    }
    @GetMapping("/get_posts")
    public List<CoursePostVO> getCoursePosts(@RequestParam Integer courseId){
        List<CoursePostVO> res=postService.getCoursePosts(courseId);
        System.out.println("controller:"+res.get(0).title);
        return res;
    }
    //发布评论、获得评论
    @PostMapping("/add_comment")
    public ResultVO<CoursePostCommentVO>addComment(@RequestParam  Integer postuid,
                                                   @RequestParam Integer courseId,
                                                   @RequestParam  String content,
                                                   @RequestParam  String posttime,
                                                   @RequestParam  Integer commentuid,
                                                   @RequestParam  String commenttime){
        System.out.println("CourseController: ");
        return postService.addCoursePostComment(postuid,courseId,content,posttime,commentuid,commenttime);
    }
    @GetMapping("/get_comments")
    public List<CoursePostCommentVO>getComments(@RequestParam Integer courseId,@RequestParam Integer postuid,@RequestParam String posttime){
        System.out.println("CourseController: "+courseId);
        return postService.getCoursePostComments(courseId,postuid,posttime);
    }
    //通知
    @PostMapping("/add_notice")
    public ResultVO<PostNoticeVO>addNotice(@RequestBody  PostNoticeVO postNotice){

        return postService.addNotice(postNotice.senderUid,postNotice.receiverUid,postNotice.sendTime,postNotice.url);
    }
    @GetMapping("/get_notice")
    public List<PostNoticeVO>getNotice(@RequestParam Integer receiverUid){
        return postService.getNotice(receiverUid);
    }
    @PostMapping("/check_notice")
    public PostNoticeVO checkNotice(@RequestParam String sendTime){
        return postService.checkNotice(sendTime);

    }
}
