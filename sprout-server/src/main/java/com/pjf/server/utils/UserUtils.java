package com.pjf.server.utils;

import com.pjf.server.entity.User;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author pjf
 * 类名：UserUtils
 * 创建时间: 2022/2/3 15:52.
 */
public class UserUtils {
    private UserUtils() {
    }

    /**
     * 获取当前登录操作员
     *
     * @return 返回操作员
     */
    public static User getCurrentUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
