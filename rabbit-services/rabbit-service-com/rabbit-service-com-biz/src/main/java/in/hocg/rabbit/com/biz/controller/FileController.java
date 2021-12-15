package in.hocg.rabbit.com.biz.controller;


import in.hocg.rabbit.com.biz.service.FileService;
import in.hocg.boot.web.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


/**
 * <p>
 * [基础模块] 文件引用表 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2020-11-11
 */
@Api(tags = "com::文件")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/file")
public class FileController {
    private final FileService service;

    @ApiOperation("上传文件")
    @PostMapping("/upload")
    public Result<String> upload(@RequestPart("file") MultipartFile file) {
        return Result.success(service.upload(file));
    }
}
