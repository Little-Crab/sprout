package com.pjf.server.controller;


import com.pjf.server.entity.Nation;
import com.pjf.server.service.INationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author pjf
 * @since 2022-02-02 18:16:55
 */
@RestController
@Tag(name = "民族管理")
@RequestMapping("/sprout/nations")
@SecurityRequirement(name = "Authorization")
public class NationController {
    @Resource
    private INationService nationService;

    @Operation(summary = "获取所有民族", description = "获取所有民族，以用来使用")
    @GetMapping("/")
    public List<Nation> getAllNations() {
        return nationService.list();
    }
}
