package com.github.lotus.mina.biz.controller.client;

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
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by hocgin on 2021/1/1
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Api(tags = "mina::小程序游戏")
@RestController
@RequestMapping({"/{appid}/game", "/game"})
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class GameClientController {
    private final GameCardService service;

    @PostMapping("/_paging")
    @ApiOperation("游戏 - 分页查询")
    public Result<List<MinaGameCardComplexVo>> paging(@PathVariable(required = false) String appid,
                                                      @Validated @RequestBody MinaGameCardPagingRo ro) {
        return Result.success(service.pagingForMina(ro).getRecords());
    }

    @GetMapping("/{id}")
    @ApiOperation("游戏 - 详情")
    public Result<GameCardComplexVo> getOne(@PathVariable(required = false) String appid, @PathVariable Long id) {
        return Result.success(service.getComplexWithMina(id));
    }

}
