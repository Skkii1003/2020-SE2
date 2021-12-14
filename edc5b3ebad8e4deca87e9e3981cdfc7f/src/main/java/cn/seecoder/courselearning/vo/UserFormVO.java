package cn.seecoder.courselearning.vo;

import lombok.Data;

@Data
public class UserFormVO {
    private String phone;
    private String password;

    public String getPhone() {
        return this.phone;
    }

    public String getPassword() {
        return this.password;
    }
}
