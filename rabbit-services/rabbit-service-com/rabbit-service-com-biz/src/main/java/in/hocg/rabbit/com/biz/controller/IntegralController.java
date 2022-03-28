package in.hocg.rabbit.com.biz.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.boot.utils.struct.result.Result;
import in.hocg.rabbit.com.biz.pojo.ro.MinaIntegralFlowPageRo;
import in.hocg.rabbit.com.biz.pojo.vo.MinaIntegralFlowVo;
import in.hocg.rabbit.com.biz.pojo.vo.MinaIntegralStatsVo;
import in.hocg.rabbit.com.biz.service.UserIntegralService;
import in.hocg.rabbit.usercontext.autoconfigure.UserContextHolder;
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
@RequestMapping("/integral")
public class IntegralController {
    private final UserIntegralService service;

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

