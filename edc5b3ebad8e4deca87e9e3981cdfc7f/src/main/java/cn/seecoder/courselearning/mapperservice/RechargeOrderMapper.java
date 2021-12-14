package cn.seecoder.courselearning.mapperservice;

import cn.seecoder.courselearning.po.RechargeOrder;
import java.util.List;

public interface RechargeOrderMapper {
    int deleteByPrimaryKey(Integer orderId);

    int insert(RechargeOrder record);

    RechargeOrder selectByPrimaryKey(Integer orderId);

    List<RechargeOrder> selectAll();

    int updateByPrimaryKey(RechargeOrder record);
}