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
    @Schema(description = "用户名", required = true)
    private String username;
    @Schema(description = "密码", required = true)
    private String password;
    @Schema(description = "验证码", required = true)
    private String code;
}
