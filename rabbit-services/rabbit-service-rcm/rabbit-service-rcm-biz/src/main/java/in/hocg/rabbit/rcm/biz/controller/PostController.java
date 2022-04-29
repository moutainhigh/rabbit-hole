package in.hocg.rabbit.rcm.biz.controller;


import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.vo.IScroll;
import in.hocg.boot.utils.struct.result.Result;
import in.hocg.rabbit.rcm.biz.pojo.ro.PostCreateRo;
import in.hocg.rabbit.rcm.biz.pojo.ro.PostScrollRo;
import in.hocg.rabbit.rcm.biz.pojo.vo.PostOptVo;
import in.hocg.rabbit.rcm.biz.pojo.vo.PostOrdinaryVo;
import in.hocg.rabbit.rcm.biz.pojo.vo.PostPublishedVo;
import in.hocg.rabbit.rcm.biz.service.PostService;
import in.hocg.rabbit.usercontext.autoconfigure.UserContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

import java.util.List;

/**
 * <p>
 * [内容模块] 帖文表 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2022-04-16
 */
@Api(tags = "帖文")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/post")
public class PostController {
    private final PostService service;

    @ApiOperation("热议 - 贴文")
    @PostMapping("/hot")
    public Result<List<PostOptVo>> hot() {
        return Result.success();
    }

    @ApiOperation("详情 - 贴文")
    @PostMapping("/{id}")
    public Result<PostPublishedVo> id(@PathVariable Long id) {
        return Result.success(service.getPostPublishedVoById(id));
    }

    @ApiOperation("滚动查询 - 贴文")
    @PostMapping("/_scroll")
    public Result<IScroll<PostOrdinaryVo>> scroll(@RequestBody PostScrollRo ro) {
        ro.setDrafted(false);
        ro.setEnabled(true);
        return Result.success(service.scroll(ro));
    }

    @ApiOperation("创建 - 贴文")
    @PostMapping("/create")
    public Result<Long> create(@RequestBody PostCreateRo ro) {
        ro.setUserId(UserContextHolder.getUserIdThrow());
        return Result.success(service.create(ro));
    }
}

