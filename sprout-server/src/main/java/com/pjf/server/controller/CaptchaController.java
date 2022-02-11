package com.pjf.server.controller;

import com.pjf.server.utils.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * @author pjf
 * @since 2022/2/10 14:06
 * 验证码控制器
 **/
@Slf4j
@RestController
@Tag(name = "验证码发送")
@RequestMapping("/captcha")
public class CaptchaController {
    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    @Operation(summary = "手机验证码")
    @GetMapping("/phone")
    public ApiResult getPhoneCode(String phone) {
        ValueOperations<String, Object> ops = redisTemplate.opsForValue();
        log.info(phone + "请求验证码");
        //模拟一个手机验证码
        String code = String.valueOf(RandomUtils.nextInt(1000, 9999));
        //放到redis中，设置60秒过期
        ops.set(phone, code, 60, TimeUnit.SECONDS);
        Long expire = ops.getOperations().getExpire(phone, TimeUnit.SECONDS);
        Map<String, String> result = new HashMap<>();
        result.put("手机验证码", code);
        result.put("过期时间", expire + "秒");
        return ApiResult.success("手机验证码", result);

    }
}
