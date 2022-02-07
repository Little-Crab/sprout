package com.pjf.server.controller;

import com.pjf.server.utils.ApiResult;
import com.pjf.server.utils.FastDFSUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author pjf
 * @since 2022/2/6 23:07
 * 文件上传和下载使用控制
 **/
@RestController
@Tag(name = "文件管理", description = "获取图片上传后的url地址")
@SecurityRequirement(name = "Authorization")
public class FileController {
    @Operation(summary = "图片上传")
    @PostMapping("/upload")
    public ApiResult imageUpload(@RequestPart("file") MultipartFile file) {
        try {
            String[] flePath = FastDFSUtils.upload(file);
            String url = FastDFSUtils.getTrackerUrl() + flePath[0] + "/" + flePath[1];
            return ApiResult.success("图片上传成功", url);
        } catch (Exception e) {
            return ApiResult.error("图片上传失败");
        }
    }
}
