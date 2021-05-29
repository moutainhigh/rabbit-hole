package com.github.lotus.mina.biz.controller.mina;

import com.github.lotus.mina.biz.pojo.ro.MinaGameCardPagingRo;
import com.github.lotus.mina.biz.pojo.vo.GameCardComplexVo;
import com.github.lotus.mina.biz.pojo.vo.MinaGameCardComplexVo;
import com.github.lotus.mina.biz.service.GameCardService;
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
 * Created by hocgin on 2021/1/1
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Api(tags = "mina::小程序游戏")
@RestController
@RequestMapping("/{appid}/game")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class MinaGameController {
    private final GameCardService service;

    @ApiOperation("游戏 - 分页查询")
    @PostMapping("/_paging")
    public Result<List<MinaGameCardComplexVo>> paging(@PathVariable String appid,
                                                      @Validated @RequestBody MinaGameCardPagingRo ro) {
        ro.setSize(Integer.MAX_VALUE);
        return Result.success(service.pagingForMina(ro).getRecords());
    }

    @ApiOperation("游戏 - 详情")
    @PostMapping("/{id}")
    public Result<GameCardComplexVo> getOne(@PathVariable String appid, @PathVariable Long id) {
        return Result.success(service.getComplexWithMina(id));
    }

}
