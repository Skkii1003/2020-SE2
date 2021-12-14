package cn.seecoder.courselearning.vo;

import cn.seecoder.courselearning.po.RechargeOrder;
import lombok.Data;

import java.util.Date;

@Data
public class RechargeOrderVO {
    private Integer orderId;

    private Integer userId;

    private Integer value;

    private Date createTime;

    public RechargeOrderVO() {
    }

    public RechargeOrderVO(RechargeOrder rechargeOrder) {
        this.orderId = rechargeOrder.getOrderId();
        this.userId = rechargeOrder.getUserId();
        this.value = rechargeOrder.getValue();
        this.createTime = rechargeOrder.getCreateTime();
    }

    public Integer getOrderId() {
        return this.orderId;
    }

    public Integer getUserId() {
        return this.userId;
    }

    public Integer getValue() {
        return this.value;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date date) {

        this.createTime=date;
    }
}