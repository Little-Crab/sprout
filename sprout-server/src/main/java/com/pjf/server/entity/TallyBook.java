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
@TableName("sp_tally_book")
@Schema(description = "TallyBook对象")
public class TallyBook implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "账本Id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "账本名称")
    @TableField("name")
    private String name;

    @Schema(description = "账本类型")
    @TableField("types_id")
    private Integer typesId;

    @Schema(description = "账本封面图Url")
    @TableField("img_url")
    private String imgUrl;

    @Schema(description = "用户Id")
    @TableField("user_id")
    private Integer userId;


}
