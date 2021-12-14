package cn.seecoder.courselearning.po.post;

public class CoursePost {
    public Integer courseId;
    public Integer uid;
    public String title;
    public String content;
    public String ctime;
    public String last_ctime;
    public CoursePost(Integer cid, Integer uid, String title, String content, String time){
        this.courseId =cid;
        this.uid=uid;
        this.title=title;
        this.content=content;
        this.ctime=time;
    }
}
