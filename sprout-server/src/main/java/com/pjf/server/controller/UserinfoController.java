package com.pjf.server.controller;


import com.pjf.server.entity.User;
import com.pjf.server.service.IUserService;
import com.pjf.server.utils.ApiResult;
import com.pjf.server.utils.FastDFSUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author pjf
 * @since 2022-02-02 12:38:14
 */
@RestController
@Tag(name = "个人中心", description = "主要为用户信息的修改，密码修改，更新头像等")
@RequestMapping("/user")
@SecurityRequirement(name = "Authorization")
public class UserinfoController {
    @Resource
    private IUserService userService;

    @Operation(summary = "更新当前用户信息")
    @PutMapping("/info")
    public ApiResult updateAdmin(@RequestBody User user, Authentication authentication) {
        if (userService.updateById(user)) {
            SecurityContextHolder.getContext().setAuthentication(
                    new UsernamePasswordAuthenticationToken(user, null, authentication.getAuthorities()));
            return ApiResult.success("更新成功");
        }
        return ApiResult.error("更新失败");
    }

    @Operation(summary = "更新用户密码")
    @PutMapping("/pass")
    public ApiResult updateAdminPassword(@RequestBody Map<String, Object> info) {
        String oldPass = (String) info.get("old_pass");
        String pass = (String) info.get("pass");
        Integer userId = (Integer) info.get("user_id");
        return userService.updateUserPassword(oldPass, pass, userId);
    }

    @Operation(summary = "更新用户头像")
    @PostMapping("/head")
    public ApiResult updateAdminUserFace(@RequestPart("file") MultipartFile file, Integer id, Authentication authentication) {
        String[] flePath = FastDFSUtils.upload(file);
        String url = FastDFSUtils.getTrackerUrl() + flePath[0] + "/" + flePath[1];
        return userService.updateUserUserFace(url, id, authentication);
    }
}
