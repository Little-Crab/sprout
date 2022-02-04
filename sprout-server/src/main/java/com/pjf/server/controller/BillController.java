package com.pjf.server.controller;


import com.pjf.server.entity.Bill;
import com.pjf.server.service.IBillService;
import com.pjf.server.utils.ApiResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.transaction.annotation.Transactional;
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
@Api(tags = "账单管理")
@RequestMapping("/bill")
public class BillController {
    @Resource
    public IBillService billService;

    @ApiOperation("获取所有账单")
    @GetMapping("/")
    public List<Bill> getAllBills(String year, String mouth, Integer tallyBookId) {
        return billService.getAllBills(year, mouth, tallyBookId);
    }

    @Transactional
    @ApiOperation("添加账单")
    @PostMapping("/")
    public ApiResult addBill(@RequestBody Bill bill) {
        return billService.addBill(bill);
    }

    @Transactional
    @ApiOperation("更新账单")
    @PutMapping("/")
    public ApiResult updateBill(@RequestBody Bill bill) {
        return billService.updateBill(bill);
    }

    @ApiOperation("删除账单")
    @DeleteMapping("/{id}")
    public ApiResult deleteBill(@PathVariable Integer id) {
        if (billService.removeById(id)) {
            return ApiResult.success("账单删除成功");
        }
        return ApiResult.error("账单删除失败");
    }
}
