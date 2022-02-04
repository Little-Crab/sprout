package com.pjf.server.controller;


import com.pjf.server.entity.TallyBook;
import com.pjf.server.service.ITallyBookService;
import com.pjf.server.utils.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * <p>
 * 前端控制器
 * </p>
 *
 * @author pjf
 * @since 2022-02-02 12:38:14
 */
@RestController
@Api(tags = "账本管理")
@RequestMapping("/tally-book")
public class TallyBookController {
    @Resource
    private ITallyBookService bookService;

    @ApiOperation("创建账本")
    @PostMapping("/")
    public ApiResult addTallyBook(@RequestBody TallyBook tallyBook) {
        return bookService.addTallyBook(tallyBook);
    }

    @ApiOperation("删除账本")
    @DeleteMapping("/{id}")
    public ApiResult deleteTallyBook(@PathVariable Integer id) {
        return bookService.deleteTallyBook(id);
    }
}
