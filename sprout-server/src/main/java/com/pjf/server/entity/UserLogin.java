package com.pjf.server.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * @author pjf
 * @since 2022/2/2 16:22
 * 登录实体
 **/
@Data
@Accessors(chain = true)
@EqualsAndHashCode
@Schema(description = "登录实体")
public class UserLogin {
    @Schema(description = "用户名")
    private String username;
    @Schema(description = "密码")
    private String password;
    @Schema(description = "手机号")
    private String phone;
    @Schema(description = "验证码")
    private String code;
    @Schema(description = "记住我")
    private Boolean rememberMe;
}
