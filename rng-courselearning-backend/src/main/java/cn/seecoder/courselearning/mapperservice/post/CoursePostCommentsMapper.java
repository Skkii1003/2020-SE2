package cn.seecoder.courselearning.mapperservice.post;

import cn.seecoder.courselearning.po.post.CoursePostComment;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CoursePostCommentsMapper {
    int insert(@Param("postuid") Integer postuid, @Param("courseId") Integer courseId,
               @Param("content") String content, @Param("posttime") String posttime,
               @Param("commentuid") Integer commentuid, @Param("commenttime")String commenttime);
    List<CoursePostComment> get(@Param("courseId")Integer courseId,
                                @Param("postuid")Integer postuid,
                                @Param("posttime")String posttime);
}
