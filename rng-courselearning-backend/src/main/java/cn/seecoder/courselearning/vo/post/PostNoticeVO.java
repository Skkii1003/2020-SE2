package cn.seecoder.courselearning.vo.post;

import cn.seecoder.courselearning.po.post.PostNotice;
import lombok.NonNull;

public class PostNoticeVO {
    public Integer senderUid;
    public Integer receiverUid;
    public String sendTime;
    public String url;
    public PostNoticeVO(){}
    public PostNoticeVO(Integer senderUid,Integer receiverUid,String sendTime,String url){
        this.senderUid=senderUid;
        this.receiverUid = receiverUid;
        this.sendTime=sendTime;
        this.url = url;
    }
    public PostNoticeVO(@NonNull PostNotice postNotice){
        this.senderUid=postNotice.senderUid;
        this.receiverUid = postNotice.receiverUid;
        this.sendTime=postNotice.sendTime;
        this.url = postNotice.url;
    }
}
