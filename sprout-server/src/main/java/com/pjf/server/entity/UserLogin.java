package com.pjf.server.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
@ApiModel(value = "登录实体")
public class UserLogin {
    @ApiModelProperty(value = "用户名", required = true)
    private String username;
    @ApiModelProperty(value = "密码", required = true)
    private String password;
    @ApiModelProperty(value = "验证码", required = true)
    private String code;
}
