package com.pjf.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
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
@TableName("sp_role")
@ApiModel(value = "Role对象")
public class Role implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("角色id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("角色英文名")
    @TableField("name")
    private String name;

    @ApiModelProperty("角色中文名")
    @TableField("name_zh")
    private String nameZh;


}
