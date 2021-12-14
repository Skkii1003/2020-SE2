package cn.seecoder.courselearning.service.post;

import cn.seecoder.courselearning.mapperservice.post.CoursePostCommentsMapper;
import cn.seecoder.courselearning.mapperservice.post.CoursePostsMapper;
import cn.seecoder.courselearning.mapperservice.post.PostNoticeMapper;
import cn.seecoder.courselearning.po.post.CoursePost;
import cn.seecoder.courselearning.po.post.CoursePostComment;
import cn.seecoder.courselearning.po.post.PostNotice;
import cn.seecoder.courselearning.service.post.PostService;
import cn.seecoder.courselearning.util.Constant;
import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.post.CoursePostCommentVO;
import cn.seecoder.courselearning.vo.post.CoursePostVO;
import cn.seecoder.courselearning.vo.post.PostNoticeVO;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

//单元测试

@RunWith(SpringRunner.class)
@SpringBootTest
class PostServiceImplTest {

    @Resource
    PostService postService;
    @Resource
    private CoursePostsMapper coursePostsMapper;
    @Resource
    private CoursePostCommentsMapper coursePostCommentsMapper;
    @Resource
    private PostNoticeMapper postNoticeMapper;

    Integer courseId;
    Integer uid;
    String title;
    String content;
    String time;

    Integer postuid;
    String posttime;
    Integer commentuid;
    String commenttime;

    Integer senderUid;
    Integer receiverUid;
    String sendTime;
    String url;


    @org.junit.jupiter.api.Test
    void addCoursePost() {
        courseId = 1;
        uid = 1;
        title = "test1";
        content = "test1_content";
        time = new Date().toString();
        CoursePostVO coursePostVO = new CoursePostVO(courseId,uid,title,content,time);
        ResultVO<CoursePostVO> ex = new ResultVO<>(Constant.REQUEST_SUCCESS,"已成功评价！", coursePostVO);
        ResultVO<CoursePostVO> re = postService.addCoursePost(courseId,uid,title,content,time);

        assertEquals(ex.getCode(),re.getCode());
        System.out.println("pass");
        assertEquals(ex.getMsg(),re.getMsg());
        System.out.println("pass");
        assertEquals(ex.getData().time,re.getData().time);
        System.out.println("pass");
    }

    @org.junit.jupiter.api.Test
    void getCoursePosts() {
        courseId = 2;
        uid = 2;
        title = "test-get";
        content = "test_content-get";
        time = new Date().toString();
        coursePostsMapper.insert(courseId,uid,title,content,time);

        List<CoursePost> coursePosts = coursePostsMapper.get(courseId);
        List<CoursePostVO> re = postService.getCoursePosts(courseId);
        int size = re.size();
        assertEquals(coursePosts.get(size-1).uid,re.get(size-1).uid);
        System.out.println("pass");
        assertEquals(coursePosts.get(size-1).content,re.get(size-1).comment);
        System.out.println("pass");
        assertEquals(coursePosts.get(size-1).ctime,re.get(size-1).time);
        System.out.println("pass");
    }

    @org.junit.jupiter.api.Test
    void addCoursePostComment() {
        postuid = 1;
        courseId = 1;
        content = "test-comment-add";
        posttime = new Date().toString();
        commentuid = 1;
        commenttime = new Date().toString();
        CoursePostCommentVO coursePostCommentVO =new CoursePostCommentVO(postuid,courseId,content,posttime,commentuid,commenttime);
        ResultVO<CoursePostCommentVO> ex = new ResultVO<>(Constant.REQUEST_SUCCESS,"已成功发表评论！", coursePostCommentVO);
        ResultVO<CoursePostCommentVO> re = postService.addCoursePostComment(postuid,courseId,content,posttime,commentuid,commenttime);
        assertEquals(ex.getCode(),re.getCode());
        System.out.println("pass");
        assertEquals(ex.getMsg(),re.getMsg());
        System.out.println("pass");
        assertEquals(ex.getData().commentuid,re.getData().commentuid);
        System.out.println("pass");
    }

    @org.junit.jupiter.api.Test
    void getCoursePostComments() {
        postuid = 2;
        courseId = 2;
        content = "test-comment-get";
        posttime = new Date().toString();
        commentuid = 2;
        commenttime = new Date().toString();
        coursePostCommentsMapper.insert(postuid,courseId,content,posttime,commentuid,commenttime);

        List<CoursePostComment> coursePostComments = coursePostCommentsMapper.get(courseId,postuid,posttime);

        List<CoursePostCommentVO> re = postService.getCoursePostComments(courseId,postuid,posttime);
        int size = re.size();
       assertEquals(coursePostComments.get(size-1).commentuid,re.get(size-1).commentuid);
        System.out.println("pass");
    }

    @org.junit.jupiter.api.Test
    void addNotice() {
        senderUid = 1;
        receiverUid = 2;
        sendTime = new Date().toString();
        url = "www.baidu.com";

        PostNoticeVO postNoticeVO =new PostNoticeVO(senderUid,receiverUid,sendTime,url);
        ResultVO<PostNoticeVO> ex = new ResultVO<>(Constant.REQUEST_SUCCESS,"已成功回复！", postNoticeVO);
        ResultVO<PostNoticeVO> re = postService.addNotice(senderUid,receiverUid,sendTime,url);

        assertEquals(ex.getData().senderUid,re.getData().senderUid);
        System.out.println("pass");
        assertEquals(ex.getData().receiverUid,re.getData().receiverUid);
        System.out.println("pass");
        assertEquals(ex.getData().sendTime,re.getData().sendTime);
        System.out.println("pass");
    }

    @org.junit.jupiter.api.Test
    void getNotice() {
        receiverUid = 2;

        List<PostNotice> postNotices = postNoticeMapper.get(receiverUid);
        List<PostNoticeVO> re = postService.getNotice(receiverUid);
        int size = re.size();

        assertEquals(postNotices.get(size-1).senderUid,re.get(size-1).senderUid);
        System.out.println("pass");
        assertEquals(postNotices.get(size-1).sendTime,re.get(size-1).sendTime);
        System.out.println("pass");
    }

    @org.junit.jupiter.api.Test
    void checkNotice() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        senderUid = 1;
        receiverUid = 2;
        sendTime = new Date().toString();
        url = "www.baidu.com";
        postNoticeMapper.insert(senderUid,receiverUid,sendTime,url);

        PostNotice postNotice = postNoticeMapper.selectByTime(sendTime).get(0);
        PostNoticeVO re = postService.checkNotice(sendTime);
        assertEquals(postNotice.senderUid,re.senderUid);
        System.out.println("pass");
        assertEquals(postNotice.receiverUid,re.receiverUid);
        System.out.println("pass");

        List<PostNotice> notices = postNoticeMapper.get(receiverUid);
        int size = notices.size();
        for(int i=0;i<size;i++){
            if(notices.get(i).senderUid==senderUid&&notices.get(i).receiverUid==receiverUid&&notices.get(i).sendTime==sendTime)
                fail("wrong");
        }
        System.out.println("pass");
    }
}