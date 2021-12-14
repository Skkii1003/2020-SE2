package cn.seecoder.courselearning.vo;

import cn.seecoder.courselearning.enums.UserRole;
import cn.seecoder.courselearning.po.User;
import lombok.Data;
import lombok.NonNull;

import java.util.Date;

@Data
public class UserVO {
    private Integer id;

    private String uname;

    private String phone;

    private String password;

    private String picture;

    private Integer balance;

    private UserRole userRole;

    private Date createTime;

    public UserVO() {
    }

    public UserVO(@NonNull User user) {
        id = user.getId();
        uname = user.getUname();
        phone = user.getPhone();
        picture = user.getPicture();
        balance = user.getBalance();
        userRole = user.getUserRole();
        createTime = user.getCreateTime();
    }
    public Integer getId(){
        return this.id;
    }
    public String getUname(){
        return this.uname;
    }

    public String getPhone() {
        return this.phone;
    }

    public String getPassword() {
        return this.password;
    }

    public String getPicture() {
        return this.picture;
    }

    public Integer getBalance() {
        return this.balance;
    }

    public UserRole getUserRole() {
        return this.userRole;
    }

    public Date getCreateTime() {
        return this.createTime;
    }

    public void setCreateTime(Date date) {
        this.createTime=date;
    }

    public void setBalance(int i) {
        this.balance=i;
    }
}
