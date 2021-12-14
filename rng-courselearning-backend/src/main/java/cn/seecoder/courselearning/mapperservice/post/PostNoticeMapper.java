package cn.seecoder.courselearning.mapperservice.post;

import cn.seecoder.courselearning.po.post.PostNotice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface PostNoticeMapper {
    int insert(@Param("senderUid") Integer senderUid,
               @Param("receiverUid") Integer receiverUid,
               @Param("sendTime") String sendTime,
               @Param("url") String url);
    List<PostNotice> get(@Param("receiverUid")Integer receiverUid);
    List<PostNotice> selectByTime(@Param("sendTime")String sendTime);
    int deleteByTime(@Param("sendTime")String sendTime);
}
