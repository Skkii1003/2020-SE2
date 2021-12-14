package cn.seecoder.courselearning.mapperservice;

import cn.seecoder.courselearning.po.Course;
import java.util.List;

public interface CourseMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Course record);

    Course selectByPrimaryKey(Integer id);

    List<Course> selectAll();

    int updateByPrimaryKey(Course record);

    List<Course> selectByType(String type);

    List<Course> queryAll(String key);

    List<Course> selectByTeacherId(Integer userId);

    List<Course> selectByStudentId(Integer userId);
}