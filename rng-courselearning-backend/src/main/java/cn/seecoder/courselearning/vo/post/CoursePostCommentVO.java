package cn.seecoder.courselearning.vo.post;

public class CoursePostCommentVO {
    public Integer postuid;
    public Integer courseId;
    public String content;
    public String posttime;
    public Integer commentuid;
    public String commenttime;
    public CoursePostCommentVO(Integer postuid,Integer courseId,String content,String posttime,Integer commentuid,String commenttime){
        this.postuid = postuid;
        this.courseId = courseId;
        this.content = content;
        this.posttime = posttime;
        this.commentuid = commentuid;
        this.commenttime = commenttime;
    }
}
