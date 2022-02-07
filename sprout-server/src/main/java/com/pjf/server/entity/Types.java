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
@Schema(description = "Types对象")
public class Types implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "类型Id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "类型名称")
    @TableField("name")
    private String name;

    @Schema(description = "类型图标")
    @TableField("icon")
    private String icon;

    @Schema(description = "类型分类，1账单。2账本。3账户")
    @TableField("type")
    private Integer type;

    @Schema(description = "备注")
    @TableField("remark")
    private String remark;


}
