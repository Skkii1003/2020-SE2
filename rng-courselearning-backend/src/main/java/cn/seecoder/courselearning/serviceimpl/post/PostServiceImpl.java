package cn.seecoder.courselearning.serviceimpl.post;

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
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;


@Service
public class PostServiceImpl implements PostService {
    @Resource
    private CoursePostsMapper coursePostsMapper;

    @Resource
    private CoursePostCommentsMapper coursePostCommentsMapper;

    @Resource
    private PostNoticeMapper postNoticeMapper;
    @Override
    public ResultVO<CoursePostVO> addCoursePost(Integer courseId, Integer uid, String title, String content, String time) {
        System.out.println("CourseServiceImpl: "+title);
        coursePostsMapper.insert(courseId,uid,title,content,time);
        CoursePostVO coursePostVO =new CoursePostVO(courseId,uid,title,content,time);
        return new ResultVO<>(Constant.REQUEST_SUCCESS,"已成功评价！", coursePostVO);
    }

    @Override
    public List<CoursePostVO> getCoursePosts(Integer courseId) {
        System.out.println("CourseServiceImpl: "+courseId);
        List<CoursePostVO> ret = new ArrayList<>();
        List<CoursePost> coursePosts = coursePostsMapper.get(courseId);
        for(CoursePost coursePost : coursePosts){
            System.out.println(coursePost.title);
            ret.add(new CoursePostVO(coursePost.courseId, coursePost.uid, coursePost.title, coursePost.content, coursePost.ctime,coursePost.last_ctime));
        }
        return ret;
    }
    //comment releated
    @Override
    public ResultVO<CoursePostCommentVO> addCoursePostComment(Integer postuid, Integer courseId, String content, String posttime, Integer commentuid, String commenttime){
        System.out.println("CourseServiceImpl: "+postuid);
        coursePostCommentsMapper.insert(postuid,courseId,content,posttime,commentuid,commenttime);
        System.out.println(posttime);
        System.out.println(commenttime);
        String ctime=posttime;
        String last_time=commenttime;
        coursePostsMapper.renew(ctime,last_time);
        CoursePostCommentVO coursePostCommentVO =new CoursePostCommentVO(postuid,courseId,content,posttime,commentuid,commenttime);
        return new ResultVO<>(Constant.REQUEST_SUCCESS,"已成功发表评论！", coursePostCommentVO);
    }
    @Override
    public List<CoursePostCommentVO> getCoursePostComments(Integer courseId,Integer postuid,String posttime){
        System.out.println("CourseServiceImpl: "+courseId);
        List<CoursePostCommentVO> ret = new ArrayList<>();
        List<CoursePostComment> coursePostComments = coursePostCommentsMapper.get(courseId,postuid,posttime);
        for(CoursePostComment coursePostComment : coursePostComments){
            ret.add(new CoursePostCommentVO(
                    coursePostComment.postuid,
                    coursePostComment.courseId,
                    coursePostComment.content,
                    coursePostComment.posttime,
                    coursePostComment.commentuid,
                    coursePostComment.commenttime));
        }
        return ret;
    }

    @Override
    public ResultVO<PostNoticeVO> addNotice(Integer senderUid,
                                     Integer receiverUid,
                                     String sendTime,
                                     String url){
        System.out.println(senderUid);
        System.out.println(receiverUid);
        System.out.println(sendTime);
        System.out.println(url);
        postNoticeMapper.insert(senderUid,receiverUid,sendTime,url);
        PostNoticeVO postNoticeVO =new PostNoticeVO(senderUid,receiverUid,sendTime,url);
        return new ResultVO<>(Constant.REQUEST_SUCCESS,"已成功回复！", postNoticeVO);
    }
    @Override
    public List<PostNoticeVO> getNotice(Integer receiverUid) {
        List<PostNoticeVO> ret = new ArrayList<>();
        List<PostNotice> postNotices = postNoticeMapper.get(receiverUid);
        for(PostNotice postNotice : postNotices){
            ret.add(new PostNoticeVO(postNotice.senderUid,
                    postNotice.receiverUid,
                    postNotice.sendTime,
                    postNotice.url));
        }
        return ret;
    }
    @Override
    public PostNoticeVO checkNotice(String sendTime) {
        PostNotice postNotice = postNoticeMapper.selectByTime(sendTime).get(0);
        PostNoticeVO ret = new PostNoticeVO(postNotice.senderUid, postNotice.receiverUid, postNotice.sendTime, postNotice.url);
        postNoticeMapper.deleteByTime(sendTime);
        return ret;

    }
}
