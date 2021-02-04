package com.github.lotus.mina.biz.controller;


import com.github.lotus.mina.biz.pojo.ro.MinaStatusMaterialPagingRo;
import com.github.lotus.mina.biz.pojo.vo.MinaStatusMaterialComplexVo;
import com.github.lotus.mina.biz.service.StatusMaterialService;
import in.hocg.boot.web.result.Result;
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
 * [小程序模块] 状态素材表 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2021-02-05
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/{appid}/status-material")
public class MinaStatusMaterialController {
    private final StatusMaterialService service;

    @ApiOperation("游戏 - 分页查询")
    @PostMapping("/_paging")
    public Result<List<MinaStatusMaterialComplexVo>> paging(@PathVariable String appid,
                                                            @Validated @RequestBody MinaStatusMaterialPagingRo ro) {
        return Result.success(service.pagingForMina(ro).getRecords());
    }

}

