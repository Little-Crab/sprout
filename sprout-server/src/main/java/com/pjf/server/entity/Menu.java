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
import java.util.List;

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
@Schema(description = "Menu对象")
public class Menu implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "菜单Id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "菜单url")
    @TableField("url")
    private String url;

    @Schema(description = "页面路径")
    @TableField("path")
    private String path;

    @Schema(description = "组件名")
    @TableField("component")
    private String component;

    @Schema(description = "名称")
    @TableField("name")
    private String name;

    @Schema(description = "图标")
    @TableField("icon_cls")
    private String iconCls;

    @Schema(description = "是否保持激活，1激活")
    @TableField("keep_alive")
    private Boolean keepAlive;

    @Schema(description = "是否需要权限，1权限")
    @TableField("require_auth")
    private Boolean requireAuth;

    @Schema(description = "父id")
    @TableField("parent_id")
    private Integer parentId;

    @Schema(description = "是否启用，1启用")
    @TableField("enabled")
    private Boolean enabled;

    @Schema(description = "子菜单")
    @TableField(exist = false)
    private List<Menu> children;

}
