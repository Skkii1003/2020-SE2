import axios from "axios";
import { ORDER_MODULE } from "./_prefix";

/**
 * 生成课程订单（可用于购买课程或赠送课程） POST course_order/create
 * @param {*} payload status 1 - 已支付成功； 2 - 待支付（若余额足够则直接付款，并将status转为1）； 3 - 已支付等待发货
 * @returns 
 */
export const createOrder = payload => {
  const { courseId, courseName, cost, userId, status } = payload;
  return axios.post(`${ORDER_MODULE}/create`, {
    courseId, courseName, cost, userId, status
  }).then(res => {
    return res.data;
  });
}

/**
 * 更新订单状态 POST course_order/update
 * @param {*} payload 
 * @returns 
 */
export const updateOrder = payload => {
  const { orderId, orderStatus } = payload;
  return axios.post(`${ORDER_MODULE}/update?orderId=${orderId}&orderStatus=${orderStatus}`).then(res => {
    return res.data;
  });
}

/**
 * 获取某用户的所有订单 GET course_order/uid/{uid}
 * @param {*} uid 
 * @returns 
 */
export const getOrdersByUser = uid => {
  return axios.get(`${ORDER_MODULE}/uid/${uid}`).then(res => {
    return res.data;
  });
}