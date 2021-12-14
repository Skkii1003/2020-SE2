package cn.seecoder.courselearning.mapperservice;

import cn.seecoder.courselearning.po.CourseOrder;
import java.util.List;

public interface CourseOrderMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(CourseOrder record);

    CourseOrder selectByPrimaryKey(Integer id);

    List<CourseOrder> selectAll();

    int updateByPrimaryKey(CourseOrder record);

    List<CourseOrder> selectByUserId(Integer userId);

    CourseOrder queryMostRecentOrder(Integer userId, Integer courseId);
}