package cn.seecoder.courselearning.mapperservice.post;

import cn.seecoder.courselearning.po.post.CoursePost;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CoursePostsMapper {
    int insert(@Param("courseId") Integer courseId, @Param("userId") Integer userId,@Param("title") String title,@Param("content") String content,@Param("time")String time);
    List<CoursePost> get(@Param("courseId")Integer courseId);
    int renew(@Param("ctime")String ctime,@Param("last_ctime")String last_ctime);
}
