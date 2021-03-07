package com.github.lotus.pay.biz.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.pay.biz.pojo.ro.PayRecordPagingRo;
import com.github.lotus.pay.biz.pojo.vo.PayRecordOrdinaryVo;
import com.github.lotus.pay.biz.service.PayRecordService;
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

/**
 * <p>
 * [支付网关] 支付记录表 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2021-01-30
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/pay-record")
public class PayRecordController {
    private final PayRecordService service;

    @ApiOperation("分页查询 - 交易记录")
    @PostMapping("/{tradeId}/_paging")
    public Result<IPage<PayRecordOrdinaryVo>> paging(@PathVariable Long tradeId,
                                                     @Validated @RequestBody PayRecordPagingRo ro) {
        return Result.success(service.pagingByTradeId(tradeId, ro));
    }
}

