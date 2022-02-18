package com.pjf.server.controller;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.pjf.server.entity.TallyBook;
import com.pjf.server.service.ITallyBookService;
import com.pjf.server.utils.ApiResult;
import com.pjf.server.utils.UserUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author pjf
 * @since 2022-02-02 12:38:14
 */
@RestController
@Tag(name = "账本管理")
@RequestMapping("/sprout/keep/tally")
@SecurityRequirement(name = "Authorization")
public class TallyBookController {
    @Resource
    private ITallyBookService bookService;

    @Operation(summary = "创建账本")
    @PostMapping("/")
    public ApiResult addTallyBook(@RequestBody TallyBook tallyBook) {
        return bookService.addTallyBook(tallyBook);
    }

    @Operation(summary = "删除账本")
    @DeleteMapping("/{id}")
    public ApiResult deleteTallyBook(@PathVariable Integer id) {
        return bookService.deleteTallyBook(id);
    }

    @Operation(summary = "更新账本")
    @PutMapping("/")
    public ApiResult updateTallyBook(@RequestBody TallyBook tallyBook) {
        if (bookService.updateById(tallyBook)) {
            return ApiResult.success("账本更新成功");
        }
        return ApiResult.error("账本更新失败");
    }

    @Operation(summary = "获取所有账本", description = "获取当前账号下所有账本信息")
    @GetMapping("/")
    public List<TallyBook> getAllTallyBooks() {
        return bookService.list(new QueryWrapper<TallyBook>().eq("user_id", UserUtils.getCurrentUser().getId()));
    }
}
