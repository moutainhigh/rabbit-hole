package com.github.lotus.mina.biz.controller;


import com.github.lotus.mina.biz.pojo.ro.AppCardPageRo;
import com.github.lotus.mina.biz.pojo.vo.AppCardComplexVo;
import com.github.lotus.mina.biz.service.AppCardService;
import in.hocg.boot.web.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * [小程序模块] 游戏表 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2021-01-01
 */
@Api(tags = "mina::小程序应用")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/{appid}/app")
public class MinaAppCardController {
    private final AppCardService service;

    @ApiOperation("应用 - 分页查询")
    @PostMapping("/_paging")
    public Result<List<AppCardComplexVo>> index(@PathVariable String appid,
                                                @Validated @RequestBody AppCardPageRo ro) {
        return Result.success(service.pagingForMina(ro).getRecords());
    }
}

