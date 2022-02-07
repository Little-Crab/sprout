package com.pjf.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;

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
@Schema(description = "Account对象,账户")
public class Account implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "账户id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "账户名称")
    @TableField("name")
    private String name;

    @Schema(description = "账户类型")
    @TableField("types_id")
    private Integer typesId;

    @Schema(description = "账户余额")
    @TableField("money")
    private BigDecimal money;

    @Schema(description = "是否计入总资产，默认为1计入，")
    @TableField("assets")
    private Boolean assets;

    @Schema(description = "用户Id")
    @TableField("user_id")
    private Integer userId;

    @Schema(description = "初始余额")
    @TableField("balance")
    private BigDecimal balance;

}
