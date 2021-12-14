package cn.seecoder.courselearning.controller;

import cn.seecoder.courselearning.service.CourseOrderService;
import cn.seecoder.courselearning.vo.CourseOrderVO;
import cn.seecoder.courselearning.vo.ResultVO;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/course_order")
public class CourseOrderController {
    @Resource
    private CourseOrderService orderService;

    /**
     * 创建课程订单
     */
    @PostMapping("/create")
    public ResultVO<CourseOrderVO> createCourseOrder(@RequestBody CourseOrderVO orderVO){
        return orderService.insertCourseOrder(orderVO);
    }

    /**
     * 根据uid查询用户所有的订单
     */
    @GetMapping("/uid/{uid}")
    public List<CourseOrderVO> getCourseOrders(@PathVariable Integer uid){
        return orderService.getCourseOrders(uid);
    }

    @PostMapping("/update")
    public ResultVO<CourseOrderVO> updateCourseOrderStatus(@RequestParam Integer orderId, @RequestParam Integer orderStatus){
        return orderService.updateCourseOrder(orderId, orderStatus);
    }

}
