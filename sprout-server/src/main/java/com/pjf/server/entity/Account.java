package com.pjf.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 
 * </p>
 *
 * @author pjf
 * @since 2022-02-02 12:38:14
 */
@Getter
@Setter
@TableName("sp_account")
@ApiModel(value = "Account对象")
public class Account implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("账户id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("账户名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("账户类型")
    @TableField("types_id")
    private Integer typesId;

    @ApiModelProperty("账户余额")
    @TableField("money")
    private BigDecimal money;

    @ApiModelProperty("是否计入总资产，默认为1计入，")
    @TableField("assets")
    private Boolean assets;

    @ApiModelProperty("用户Id")
    @TableField("user_id")
    private Integer userId;

    @ApiModelProperty("初始余额")
    @TableField("balance")
    private BigDecimal balance;

}
