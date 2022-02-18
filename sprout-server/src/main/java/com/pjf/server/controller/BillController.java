package com.pjf.server.controller;


import com.pjf.server.entity.Bill;
import com.pjf.server.service.IBillService;
import com.pjf.server.utils.ApiResult;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "账单管理")
@RequestMapping("/sprout/keep/bill")
@SecurityRequirement(name = "Authorization")
public class BillController {
    @Resource
    public IBillService billService;

    @Operation(summary = "获取所有账单")
    @GetMapping("/")
    public List<Bill> getAllBills(String year, String month, Integer tallyBookId) {
        return billService.getAllBills(year, month, tallyBookId);
    }

    @Transactional
    @Operation(summary = "添加账单")
    @PostMapping("/")
    public ApiResult addBill(@RequestBody Bill bill) {
        return billService.addBill(bill);
    }

    @Transactional
    @Operation(summary = "更新账单")
    @PutMapping("/")
    public ApiResult updateBill(@RequestBody Bill bill) {
        return billService.updateBill(bill);
    }

    @Operation(summary = "删除账单")
    @DeleteMapping("/{id}")
    public ApiResult deleteBill(@PathVariable Integer id) {
        if (billService.removeById(id)) {
            return ApiResult.success("账单删除成功");
        }
        return ApiResult.error("账单删除失败");
    }
}
