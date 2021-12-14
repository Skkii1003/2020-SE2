package cn.seecoder.courselearning.service;

import cn.seecoder.courselearning.vo.RechargeOrderVO;
import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.UserVO;
import org.springframework.stereotype.Service;


public interface UserService {
    // 用户注册
    ResultVO<UserVO> userRegister(UserVO user);
    // 用户登录验证
    ResultVO<UserVO> userLogin(String phone, String password);
    // 根据id查找用户
    UserVO getUser(Integer uid);
    // 账号充值
    ResultVO<UserVO> rechargeAccount(RechargeOrderVO rechargeOrderVO);
}
