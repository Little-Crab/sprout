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
@TableName("sp_types")
@ApiModel(value = "Types对象", description = "")
public class Types implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("类型Id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("类型名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("类型图标")
    @TableField("icon")
    private String icon;

    @ApiModelProperty("类型分类，1账单。2账本。3账户")
    @TableField("type")
    private Integer type;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;


}
