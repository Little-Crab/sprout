package com.pjf.server.controller;


import com.pjf.server.entity.Types;
import com.pjf.server.service.ITypesService;
import com.pjf.server.utils.ApiResult;
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
@Tag(name = "类型管理", description = "账单类型，账本类型，账户类型，分别存储了不同的图标和描述")
@RequestMapping("/sprout/system/keep/types")
@SecurityRequirement(name = "Authorization")
public class TypesController {
    @Resource
    private ITypesService typesService;

    @Operation(summary = "获取类型列表", description = "根据不同的类型分类查询类型，且为账单类型时可具有父Id")
    @GetMapping("/")
    public List<Types> getAllTypesByType(Integer type) {
        return typesService.getAllTypesByType(type);
    }

    @Operation(summary = "增加类型", description = "分别增加类型，接口相同，仅仅传入参数不同")
    @PostMapping("/")
    public ApiResult addType(@RequestBody Types types) {
        if (typesService.save(types)) {
            return ApiResult.success("类型添加成功");
        }
        return ApiResult.error("类型添加失败");
    }

    @Operation(summary = "删除类型", description = "根据Id删除,并且有子id时不允许删除")
    @DeleteMapping("/{id}")
    public ApiResult deleteType(@PathVariable Integer id) {
        if (typesService.removeById(id)) {
            return ApiResult.success("类型删除成功");
        }
        return ApiResult.error("类型删除失败");
    }

    @Operation(summary = "更新类型", description = "更新图标或者名称")
    @PutMapping("/")
    public ApiResult updateType(@RequestBody Types type) {
        if (typesService.updateById(type)) {
            return ApiResult.success("类型更新成功");
        }
        return ApiResult.error("类型更新失败");
    }
}
