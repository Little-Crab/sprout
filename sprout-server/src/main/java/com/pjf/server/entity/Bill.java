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
import java.time.LocalDate;

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
@TableName("sp_bill")
@Schema(description = "Bill对象")
public class Bill implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "每笔账单ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "钱数")
    @TableField("money")
    private BigDecimal money;

    @Schema(description = "日期")
    @TableField("create_date")
    private LocalDate createDate;

    @Schema(description = "账本id")
    @TableField("book_id")
    private Integer bookId;

    @Schema(description = "账户Id")
    @TableField("account_id")
    private Integer accountId;

    @Schema(description = "类型Id")
    @TableField("types_id")
    private Integer typesId;

    @Schema(description = "是否属于报销，默认为0：false，1:true")
    @TableField("claimed")
    private Boolean claimed;

    @Schema(description = "图片Url")
    @TableField("img_url")
    private String imgUrl;

    @Schema(description = "是否已经报销，默认为0：false")
    @TableField("reimbursed")
    private Boolean reimbursed;

    @Schema(description = "备注")
    @TableField("remark")
    private String remark;

    @Schema(description = "用户Id")
    @TableField("user_id")
    private Integer userId;


}
