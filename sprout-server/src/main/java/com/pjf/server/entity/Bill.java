package com.pjf.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serial;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
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
@TableName("sp_bill")
@ApiModel(value = "Bill对象", description = "")
public class Bill implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("每笔账单ID")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("钱数")
    @TableField("money")
    private BigDecimal money;

    @ApiModelProperty("日期")
    @TableField("create_date")
    private LocalDate createDate;

    @ApiModelProperty("账本id")
    @TableField("book_id")
    private Integer bookId;

    @ApiModelProperty("账户Id")
    @TableField("account_id")
    private Integer accountId;

    @ApiModelProperty("类型Id")
    @TableField("types_id")
    private Integer typesId;

    @ApiModelProperty("是否属于报销，默认为0：false，1:true")
    @TableField("claimed")
    private Boolean claimed;

    @ApiModelProperty("图片Url")
    @TableField("img_url")
    private String imgUrl;

    @ApiModelProperty("是否已经报销，默认为0：false")
    @TableField("reimbursed")
    private Boolean reimbursed;

    @ApiModelProperty("备注")
    @TableField("remark")
    private String remark;

    @ApiModelProperty("用户Id")
    @TableField("user_id")
    private Integer userId;


}
