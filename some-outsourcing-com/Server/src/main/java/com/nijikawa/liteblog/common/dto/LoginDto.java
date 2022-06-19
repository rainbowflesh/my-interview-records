package com.nijikawa.liteblog.common.dto;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.io.Serializable;

/**
 * 登录 DTO
 *
 * @author nijikawa
 */
@Data
public class LoginDto implements Serializable {
    private static final long serialVersionUID = -3282894114652892574L;
    @NotBlank(message = "昵称不能为空")
    private String username;
    @NotBlank(message = "密码不能为空")
    private String password;
    private void readObject(java.io.ObjectInputStream in) throws ClassNotFoundException
            , java.io.NotSerializableException {
        throw new java.io.NotSerializableException("com.nijikawa.liteblog.common.dto.LoginDto");
    }
    private void writeObject(java.io.ObjectOutputStream out) throws java.io.NotSerializableException {
        throw new java.io.NotSerializableException("com.nijikawa.liteblog.common.dto.LoginDto");
    }
}
