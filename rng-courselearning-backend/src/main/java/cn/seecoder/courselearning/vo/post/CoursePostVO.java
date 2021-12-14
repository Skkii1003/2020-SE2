package cn.seecoder.courselearning.vo.post;

public class CoursePostVO {
    public Integer cid;
    public Integer uid;
    public String title;
    public String comment;
    public String time;
    public String last_ctime;
    public CoursePostVO(Integer cid, Integer uid, String title, String content, String time){
        this.cid=cid;
        this.uid=uid;
        this.title=title;
        this.comment=content;
        this.time=time;
    }
    public CoursePostVO(Integer cid, Integer uid, String title, String content, String time,String last_ctime){
        this.cid=cid;
        this.uid=uid;
        this.title=title;
        this.comment=content;
        this.time=time;
        this.last_ctime = last_ctime;
    }
}
