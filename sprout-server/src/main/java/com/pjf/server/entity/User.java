package com.pjf.server.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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
@ToString
@TableName("sp_user")
@Schema(description = "User对象")
public class User implements Serializable, UserDetails {
    @Serial
    private static final long serialVersionUID = 1L;

    @Schema(description = "用户id")
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    @Schema(description = "用户姓名")
    @TableField("name")
    private String name;

    @Schema(description = "用户性别")
    @TableField("gender")
    private String gender;

    @Schema(description = "用户昵称")
    @TableField("nickname")
    private String nickname;

    @Schema(description = "账号")
    @TableField("username")
    private String username;

    @Schema(description = "密码")
    @TableField("password")
    private String password;

    @Schema(description = "用户头像")
    @TableField("img_url")
    private String imgUrl;

    @Schema(description = "手机号")
    @TableField("phone")
    private String phone;

    @Schema(description = "地址")
    @TableField("address")
    private String address;

    @Schema(description = "民族Id")
    @TableField("notion_id")
    private Integer notionId;

    @Schema(description = "邮箱")
    @TableField("email")
    private String email;

    @Schema(description = "是否启用，1为true启用，0为未启用")
    @TableField("enabled")
    @Getter(value = AccessLevel.NONE)
    private Boolean enabled;

    @Schema(description = "身份证号")
    @TableField("id_card")
    private String idCard;

    @Schema(description = "出生日期")
    @TableField("birthday")
    private LocalDate birthday;

    @Schema(description = "注册日期")
    @TableField("create_date")
    private LocalDate createDate;

    @Schema(description = "角色")
    @TableField(exist = false)
    private List<Role> roles;

    @Schema(description = "1,为未锁定，0为锁定")
    @TableField("accountNonLocked")
    @Getter(value = AccessLevel.NONE)
    private Boolean accountNonLocked;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.
                stream().map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }

    @Override
    public boolean isAccountNonExpired() {
        return false;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
