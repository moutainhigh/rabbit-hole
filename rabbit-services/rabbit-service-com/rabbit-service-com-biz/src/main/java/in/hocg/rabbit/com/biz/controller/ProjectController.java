package in.hocg.rabbit.com.biz.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.boot.validation.group.Insert;
import in.hocg.boot.validation.group.Update;
import in.hocg.rabbit.com.api.pojo.vo.ProjectComplexVo;
import in.hocg.rabbit.com.biz.pojo.ro.ProjectCompleteRo;
import in.hocg.rabbit.com.biz.pojo.ro.ProjectPagingRo;
import in.hocg.rabbit.com.biz.pojo.ro.ProjectSaveRo;
import in.hocg.rabbit.com.biz.service.ProjectService;
import in.hocg.rabbit.usercontext.autoconfigure.UserContextHolder;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import in.hocg.boot.web.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * [通用模块] 项目表 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2021-01-13
 */
@Api(tags = "com::项目")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/project")
public class ProjectController {
    private final ProjectService service;

    @PostMapping("/_complete")
    @ApiOperation("检索 - 项目")
    @ApiOperationSupport(author = "hocgin")
    public Result<List<ProjectComplexVo>> complete(@Validated @RequestBody ProjectCompleteRo ro) {
        return Result.success(service.complete(ro));
    }

    @ApiOperation("查询详情 - 项目")
    @GetMapping("/{id:\\d+}")
    public Result<ProjectComplexVo> getComplex(@ApiParam(value = "项目", required = true) @PathVariable Long id) {
        return Result.success(service.getComplex(id));
    }

    @ApiOperation("新增项目 - 项目")
    @PostMapping
    public Result<Long> insertOne(@Validated({Insert.class}) @RequestBody ProjectSaveRo ro) {
        ro.setUserId(UserContextHolder.getUserIdThrow());
        service.insertOne(ro);
        return Result.success();
    }

    @ApiOperation("修改项目 - 项目")
    @PutMapping("/{id}")
    public Result<Void> updateOne(@ApiParam(value = "项目", required = true) @PathVariable Long id,
                                  @Validated({Update.class}) @RequestBody ProjectSaveRo ro) {
        ro.setUserId(UserContextHolder.getUserIdThrow());
        service.updateOne(id, ro);
        return Result.success();
    }

    @ApiOperation("分页查询项目 - 项目")
    @PostMapping("/_paging")
    public Result<IPage<ProjectComplexVo>> paging(@Validated @RequestBody ProjectPagingRo ro) {
        return Result.success(service.paging(ro));
    }

    @ApiOperation("删除项目 - 项目")
    @DeleteMapping("/{id}")
    public Result<Void> deleteOne(@ApiParam(value = "项目", required = true) @PathVariable Long id) {
        service.deleteOne(id);
        return Result.success();
    }

    @ApiOperation("详情 - 项目")
    @ApiOperationSupport(author = "hocgin")
    @GetMapping("/encoding:{encoding}")
    public Result<ProjectComplexVo> getByEncoding(@PathVariable("encoding") String encoding) {
        return Result.success(service.getByEncoding(encoding));
    }
}

