package com.github.lotus.com.biz.controller.mina;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.com.biz.pojo.ro.MinaIntegralFlowPageRo;
import com.github.lotus.com.biz.pojo.vo.MinaIntegralFlowVo;
import com.github.lotus.com.biz.pojo.vo.MinaIntegralStatsVo;
import com.github.lotus.com.biz.service.UserIntegralService;
import com.github.lotus.usercontext.autoconfigure.UserContextHolder;
import in.hocg.boot.web.result.Result;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * [通用] 用户积分表 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2021-06-26
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/mina/integral")
public class MinaIntegralController {
    private UserIntegralService service;

    @ApiOperation("获取状态 - 我的积分")
    @GetMapping("/stat")
    public Result<MinaIntegralStatsVo> stats() {
        return Result.success(UserContextHolder.getUserId().map(service::getStats).orElse(null));
    }

    @ApiOperation("获取流水 - 我的积分")
    @PostMapping("/_flow")
    public Result<IPage<MinaIntegralFlowVo>> pageFlow(MinaIntegralFlowPageRo ro) {
        ro.setUserId(UserContextHolder.getUserIdThrow());
        return Result.success(service.pageFlow(ro));
    }
}

