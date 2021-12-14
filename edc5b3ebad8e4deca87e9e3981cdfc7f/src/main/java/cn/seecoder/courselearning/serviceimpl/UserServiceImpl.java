package cn.seecoder.courselearning.serviceimpl;

import cn.seecoder.courselearning.mapperservice.RechargeOrderMapper;
import cn.seecoder.courselearning.mapperservice.UserMapper;
import cn.seecoder.courselearning.po.RechargeOrder;
import cn.seecoder.courselearning.po.User;
import cn.seecoder.courselearning.service.UserService;
import cn.seecoder.courselearning.util.Constant;
import cn.seecoder.courselearning.vo.RechargeOrderVO;
import cn.seecoder.courselearning.vo.ResultVO;
import cn.seecoder.courselearning.vo.UserVO;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.Date;


@Service
public class UserServiceImpl implements UserService {
    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    @Resource
    UserMapper userMapper;
    @Resource
    RechargeOrderMapper rechargeOrderMapper;

    @Override
    public ResultVO<UserVO> userRegister(UserVO vo) {
        String phone = vo.getPhone();
        String uname = vo.getUname();
        String password = vo.getPassword();
        if(userMapper.selectByPhone(phone) == null){
            if(StringUtils.hasText(uname) && StringUtils.hasText(password)){
                vo.setCreateTime(new Date());
                vo.setBalance(0);
                User user = new User(vo);
                userMapper.insert(user);
                System.out.println(new UserVO(user));
                return new ResultVO<>(Constant.REQUEST_SUCCESS, "账号注册成功！", new UserVO(user));
            }else{
                return new ResultVO<>(Constant.REQUEST_FAIL, "用户名或密码不得为空！");
            }
        }
        return new ResultVO<>(Constant.REQUEST_FAIL, "这个手机号已经注册过账号。");
    }

    @Override
    public ResultVO<UserVO> userLogin(String phone, String password) {
        User user = userMapper.selectByPhone(phone);
        if(user == null){
            return new ResultVO<>(Constant.REQUEST_FAIL, "这个手机号尚未注册过账号。");
        }else{
            if(!user.getPassword().equals(password))
                return new ResultVO<>(Constant.REQUEST_FAIL, "账号或密码错误！");
        }
        return new ResultVO<>(Constant.REQUEST_SUCCESS, "账号登陆成功！", new UserVO(userMapper.selectByPhone(phone)));
    }

    @Override
    public UserVO getUser(Integer uid) {
        User userFromDB = userMapper.selectByPrimaryKey(uid);
        if(userFromDB == null){
            return new UserVO();
        }else{
            return new UserVO(userFromDB);
        }
    }

    @Override
    public ResultVO<UserVO> rechargeAccount(RechargeOrderVO rechargeOrderVO) {
        ResultVO<UserVO> ret = new ResultVO<>();
        Integer userId = rechargeOrderVO.getUserId();
        if(userId == null || userId <= 0){
            ret.setCode(Constant.REQUEST_FAIL);
            ret.setMsg("userId 参数无效");
        }else{
            User user = userMapper.selectByPrimaryKey(userId);
            if(user != null){
                try{
                    rechargeOrderVO.setCreateTime(new Date());
                    Integer value = rechargeOrderVO.getValue();
                    // 更新用户余额
                    userMapper.increaseBalance(userId, value);
                    if(rechargeOrderMapper.insert(new RechargeOrder(rechargeOrderVO)) > 0){
                        ret.setCode(Constant.REQUEST_SUCCESS);
                        ret.setMsg("充值成功");
                        ret.setData(new UserVO(userMapper.selectByPrimaryKey(rechargeOrderVO.getUserId())));
                    }else{
                        ret.setCode(Constant.REQUEST_FAIL);
                        ret.setMsg("系统错误");
                    }
                }catch (Exception e){
                    ret.setCode(Constant.REQUEST_FAIL);
                    ret.setMsg("服务器数据库系统出错，请咨询系统管理员");
                    logger.error("用户充值账号时出错", e);
                }
            }
        }
        return ret;
    }
}
