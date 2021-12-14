package cn.seecoder.courselearning.service;

import cn.seecoder.courselearning.vo.CourseOrderVO;
import cn.seecoder.courselearning.vo.ResultVO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface CourseOrderService {
    // 创建订单
    ResultVO<CourseOrderVO> insertCourseOrder(CourseOrderVO orderVO);
    // 更新订单
    ResultVO<CourseOrderVO> updateCourseOrder(Integer orderId, Integer orderStatus);
    // 根据用户id获取该用户的所有订单
    List<CourseOrderVO> getCourseOrders(Integer uid);
}
