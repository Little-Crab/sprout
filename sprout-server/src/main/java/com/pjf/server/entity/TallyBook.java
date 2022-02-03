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
@TableName("sp_tally_book")
@ApiModel(value = "TallyBook对象", description = "")
public class TallyBook implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("账本Id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("账本名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("账本类型")
    @TableField("types_id")
    private Integer typesId;

    @ApiModelProperty("账本封面图Url")
    @TableField("img_url")
    private String imgUrl;

    @ApiModelProperty("用户Id")
    @TableField("user_id")
    private Integer userId;


}
