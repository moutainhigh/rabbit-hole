package in.hocg.rabbit.rcm.biz.controller;


import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.ro.ScrollRo;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.vo.IScroll;
import in.hocg.boot.utils.struct.result.Result;
import in.hocg.rabbit.rcm.biz.pojo.ro.CreateVersionDocRo;
import in.hocg.rabbit.rcm.biz.pojo.ro.PushDocContentRo;
import in.hocg.rabbit.rcm.biz.pojo.ro.RollbackDocRo;
import in.hocg.rabbit.rcm.biz.pojo.vo.*;
import in.hocg.rabbit.rcm.biz.service.DocService;
import in.hocg.rabbit.usercontext.autoconfigure.UserContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * [内容模块] 文档对象表 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2022-02-23
 */
@Api(tags = {"rcm::文档", "rcm"})
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/doc")
public class DocController {
    private final DocService service;

    @ApiOperation("获取已发布内容")
    @GetMapping("/{id:\\d+}")
    public Result<PublishedDocVo> doc(@PathVariable Long id) {
        return Result.success(service.getPublishedById(id));
    }

    @ApiOperation("获取待编辑内容")
    @GetMapping("/{id:\\d+}/content")
    public Result<DraftDocVo> content(@PathVariable Long id) {
        Long userId = UserContextHolder.getUserIdThrow();
        return Result.success(service.getDraftedById(id, userId));
    }

    @ApiOperation("提交草稿")
    @PutMapping("/{id:\\d+}/content")
    public Result<Void> pushDrafted(@PathVariable Long id, @RequestBody PushDocContentRo ro) {
        Long userId = UserContextHolder.getUserIdThrow();
        service.pushDraftedByOwnerUser(id, ro, userId);
        return Result.success();
    }

    @ApiOperation("发布最新的草稿")
    @PutMapping("/{id:\\d+}/publish")
    public Result<Void> pushPublished(@PathVariable Long id) {
        Long userId = UserContextHolder.getUserIdThrow();
        service.pushPublishedByOwnerUser(id, userId);
        return Result.success();
    }

    @ApiOperation("查看编辑历史")
    @PostMapping("/{id:\\d+}/history")
    public Result<IScroll<HistoryDocContentVo>> history(@PathVariable Long id, @RequestBody ScrollRo ro) {
        Long userId = UserContextHolder.getUserIdThrow();
        return Result.success(service.historyByIdAndOwnerUser(id, ro, userId));
    }

    @ApiOperation("恢复版本")
    @PutMapping("/{id:\\d+}/rollback")
    public Result<Void> rollback(@PathVariable Long id, @RequestBody RollbackDocRo ro) {
        Long userId = UserContextHolder.getUserIdThrow();
        service.rollbackVersion(id, ro, userId);
        return Result.success();
    }

    @ApiOperation("保存为版本")
    @PostMapping("/version/{id:\\d+}/name")
    public Result<Void> saveVersion(@PathVariable("id") Long contentId, @RequestBody CreateVersionDocRo ro) {
        Long userId = UserContextHolder.getUserIdThrow();
        service.createVersion(contentId, ro, userId);
        return Result.success();
    }
}

