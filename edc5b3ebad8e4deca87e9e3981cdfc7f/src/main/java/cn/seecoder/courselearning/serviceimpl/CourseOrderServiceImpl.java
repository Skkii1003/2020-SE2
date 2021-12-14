package cn.seecoder.courselearning.serviceimpl;

import cn.seecoder.courselearning.mapperservice.CourseOrderMapper;
import cn.seecoder.courselearning.mapperservice.UserMapper;
import cn.seecoder.courselearning.po.CourseOrder;
import cn.seecoder.courselearning.po.User;
import cn.seecoder.courselearning.service.CourseOrderService;
import cn.seecoder.courselearning.util.Constant;
import cn.seecoder.courselearning.vo.CourseOrderVO;
import cn.seecoder.courselearning.vo.ResultVO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

@Service
public class CourseOrderServiceImpl implements CourseOrderService {
    @Resource
    CourseOrderMapper orderMapper;
    @Resource
    UserMapper userMapper;

    @Override
    public ResultVO<CourseOrderVO> insertCourseOrder(CourseOrderVO orderVO) {
        CourseOrder order;
        try{
            if((order = orderMapper.queryMostRecentOrder(orderVO.getUserId(), orderVO.getCourseId())) != null
                && !order.getStatus().equals(Constant.ORDER_STATUS_CANCEL)){
                return new ResultVO<>(Constant.REQUEST_FAIL, "当前用户已经购买过此课程，或者有上一笔未支付的订单", new CourseOrderVO(order));
            }
            order = new CourseOrder(orderVO);
            Integer status = order.getStatus();
            if(status.equals(Constant.ORDER_STATUS_UNPAID)){
                // 当订单状态为未支付时，检查用户余额是否足够
                User user = userMapper.selectByPrimaryKey(order.getUserId());
                if(user.getBalance()>=orderVO.getCost()){
                    userMapper.decreaseBalance(user.getId(), order.getCost());
                    // 支付成功，将订单存入数据库
                    order.setStatus(Constant.ORDER_STATUS_SUCCESS);
                    orderMapper.insert(order);
                }else {
                    // 将订单存入数据库
                    orderMapper.insert(order);
                    // 若用户余额不足，则不保存订单，返回失败信息
                    return new ResultVO<>(Constant.REQUEST_FAIL, "用户余额不足，未支付订单已保存", new CourseOrderVO(order));
                }
            }else if(status.equals(Constant.ORDER_STATUS_SUCCESS) || status.equals(Constant.ORDER_STATUS_WAIT)) {
                // 若订单状态为已支付或等待发货，则直接存入数据库
                orderMapper.insert(order);
                return new ResultVO<>(Constant.REQUEST_SUCCESS, "订单保存成功", new CourseOrderVO(order));
            }
        }catch (Exception e){
            return new ResultVO<>(Constant.REQUEST_FAIL, e.toString());
        }
        return new ResultVO<>(Constant.REQUEST_SUCCESS, "课程购买成功", new CourseOrderVO(order));
    }

    @Override
    public ResultVO<CourseOrderVO> updateCourseOrder(Integer orderId, Integer orderStatus) {
        CourseOrder order = orderMapper.selectByPrimaryKey(orderId);
        if(order == null) return new ResultVO<>(Constant.REQUEST_FAIL, "找不到当前订单！");
        if(order.getStatus().equals(Constant.ORDER_STATUS_SUCCESS))
            return new ResultVO<>(Constant.REQUEST_FAIL, "当前订单已完成交易，不支持再次修改订单状态！", new CourseOrderVO(order));
        if((orderStatus.equals(Constant.ORDER_STATUS_SUCCESS)||orderStatus.equals(Constant.ORDER_STATUS_WAIT)) &&
                order.getStatus().equals(Constant.ORDER_STATUS_UNPAID)){
            // 当订单原始状态为待支付 并且此时为支付成功时，需扣除用户余额
            User user = userMapper.selectByPrimaryKey(order.getUserId());
            if(user.getBalance()>=order.getCost()){
                userMapper.decreaseBalance(user.getId(), order.getCost());
            }else
                return new ResultVO<>(Constant.REQUEST_FAIL, "用户余额不足");
        }
        order.setStatus(orderStatus);
        orderMapper.updateByPrimaryKey(order);
        return new ResultVO<>(Constant.REQUEST_SUCCESS, "课程购买成功", new CourseOrderVO(order));
    }

    @Override
    public List<CourseOrderVO> getCourseOrders(Integer uid) {
        List<CourseOrderVO> ret = new ArrayList<>();
        List<CourseOrder> orderList = orderMapper.selectByUserId(uid);
        for(CourseOrder order: orderList){
            ret.add(new CourseOrderVO(order));
        }
        return ret;
    }
}
