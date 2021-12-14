package cn.seecoder.courselearning.po.post;

public class PostNotice {
    public Integer senderUid;
    public Integer receiverUid;
    public String sendTime;
    public String url;
    public PostNotice(Integer senderUid,Integer receiverUid,String sendTime,String url){
        this.senderUid=senderUid;
        this.receiverUid = receiverUid;
        this.sendTime=sendTime;
        this.url = url;
    }

}
