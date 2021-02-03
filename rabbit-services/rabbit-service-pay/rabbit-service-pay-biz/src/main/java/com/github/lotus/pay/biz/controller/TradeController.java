package com.github.lotus.pay.biz.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.pay.biz.pojo.ro.TradeCompleteRo;
import com.github.lotus.pay.biz.pojo.ro.TradePagingRo;
import com.github.lotus.pay.biz.pojo.vo.TradeComplexVo;
import com.github.lotus.pay.biz.service.TradeService;
import in.hocg.boot.logging.autoconfiguration.core.UseLogger;
import in.hocg.boot.web.result.Result;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * [支付网关] 交易账单表 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2021-01-30
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/trade")
public class TradeController {
    private final TradeService service;

    @ApiOperation("分页查询 - 交易账单")
    @PostMapping("/_paging")
    public Result<IPage<TradeComplexVo>> paging(@Validated @RequestBody TradePagingRo ro) {
        return Result.success(service.paging(ro));
    }

    @ApiOperation("检索 - 交易账单")
    @UseLogger("检索 - 交易账单")
    @PostMapping("/_complete")
    public Result<List<TradeComplexVo>> complete(@Validated @RequestBody TradeCompleteRo ro) {
        return Result.success(service.complete(ro));
    }

    @ApiOperation("查询详情 - 交易账单")
    @GetMapping("/{id}")
    public Result<TradeComplexVo> getComplex(@ApiParam(value = "交易账单", required = true) @PathVariable Long id) {
        return Result.success(service.getComplex(id));
    }
}

