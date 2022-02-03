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
@TableName("sp_menu")
@ApiModel(value = "Menu对象")
public class Menu implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("菜单Id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @ApiModelProperty("菜单url")
    @TableField("url")
    private String url;

    @ApiModelProperty("页面路径")
    @TableField("path")
    private String path;

    @ApiModelProperty("组件名")
    @TableField("component")
    private String component;

    @ApiModelProperty("名称")
    @TableField("name")
    private String name;

    @ApiModelProperty("图标")
    @TableField("icon_cls")
    private String iconCls;

    @ApiModelProperty("是否保持激活，1激活")
    @TableField("keep_alive")
    private Boolean keepAlive;

    @ApiModelProperty("是否需要权限，1权限")
    @TableField("require_auth")
    private Boolean requireAuth;

    @ApiModelProperty("父id")
    @TableField("parent_id")
    private Integer parentId;

    @ApiModelProperty("是否启用，1启用")
    @TableField("enabled")
    private Boolean enabled;


}
