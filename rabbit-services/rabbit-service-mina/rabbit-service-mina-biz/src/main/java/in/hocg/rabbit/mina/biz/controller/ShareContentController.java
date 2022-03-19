package in.hocg.rabbit.mina.biz.controller;

import in.hocg.boot.utils.struct.result.Result;
import in.hocg.rabbit.mina.biz.pojo.ro.ShareContentSaveRo;
import in.hocg.rabbit.mina.biz.pojo.vo.ShareContentVo;
import in.hocg.rabbit.mina.biz.service.ShareContentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hocgin on 2022/3/16
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Validated
@RestController
@Api(tags = "mina::分享内容")
@RequestMapping("/share-content")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ShareContentController {
    private final ShareContentService service;

    @GetMapping
    @ApiOperation("查询 - 分享内容")
    public Result<ShareContentVo> get(@RequestParam("encoding") String encoding) {
        return Result.success(service.getByEncoding(encoding));
    }

    @PostMapping
    @ApiOperation("创建 - 分享内容")
    public Result<String> create(@RequestBody ShareContentSaveRo ro) {
        return Result.success(service.create(ro));
    }
}
