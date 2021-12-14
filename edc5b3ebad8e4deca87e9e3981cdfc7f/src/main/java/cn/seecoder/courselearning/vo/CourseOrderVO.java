package cn.seecoder.courselearning.vo;

import cn.seecoder.courselearning.po.CourseOrder;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
public class CourseOrderVO {
    private Integer id;

    private Integer cost;

    private Integer courseId;

    private String courseName;

    private Date createTime;

    private Integer userId;

    private Integer status;

    public CourseOrderVO() {
    }

    public CourseOrderVO(@NonNull CourseOrder order) {
        id = order.getId();
        cost = order.getCost();
        courseId = order.getCourseId();
        courseName = order.getCourseName();
        createTime = order.getCreateTime();
        userId = order.getUserId();
        status = order.getStatus();
    }

    public Integer getId() {
        return this.id;
    }

    public Integer getCost() {
        return this.cost;
    }

    public Integer getCourseId() {
        return this.courseId;
    }

    public String getCourseName() {
        return this.courseName;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public Integer getStatus() {
        return this.status;
    }
}
