package in.hocg.rabbit.wl.biz.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.rabbit.usercontext.autoconfigure.UserContextHolder;
import in.hocg.rabbit.wl.biz.pojo.ro.company.CompanyCompleteRo;
import in.hocg.rabbit.wl.biz.pojo.ro.company.CompanyCreateRo;
import in.hocg.rabbit.wl.biz.pojo.ro.company.CompanyDeleteRo;
import in.hocg.rabbit.wl.biz.pojo.ro.company.CompanyPagingRo;
import in.hocg.rabbit.wl.biz.pojo.ro.company.CompanyUpdateRo;
import in.hocg.rabbit.wl.biz.pojo.vo.CompanyComplexVo;
import in.hocg.rabbit.wl.biz.service.CompanyService;
import in.hocg.boot.utils.struct.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
 * [物流模块] 公司表 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2020-11-12
 */
@Api(tags = "wl::物流公司")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/company")
public class CompanyController {
    private final CompanyService service;

    @PostMapping
    @ApiOperation("创建 - 物流公司")
    public Result<Void> create(@Validated @RequestBody CompanyCreateRo ro) {
        Long userId = UserContextHolder.getUserIdThrow();
        ro.setCreator(userId);
        service.create(ro);
        return Result.success();
    }

    @PutMapping("/{id}")
    @ApiOperation("更新 - 物流公司")
    public Result<Void> update(@PathVariable("id") Long id,
                       @Validated @RequestBody CompanyUpdateRo ro) {
        Long userId = UserContextHolder.getUserIdThrow();
        ro.setUpdater(userId);
        service.update(id, ro);
        return Result.success();
    }

    @DeleteMapping
    @ApiOperation("删除 - 物流公司")
    public Result<Void> deletes(@Validated @RequestBody CompanyDeleteRo ro) {
        service.delete(ro);
        return Result.success();
    }

    @GetMapping("/{id}")
    @ApiOperation("详情 - 物流公司")
    public Result<CompanyComplexVo> query(@PathVariable("id") Long id) {
        return Result.success(service.getCompany(id));
    }

    @PostMapping("/_paging")
    @ApiOperation("分页查询 - 物流公司")
    public Result<IPage<CompanyComplexVo>> paging(@Validated @RequestBody CompanyPagingRo ro) {
        return Result.success(service.paging(ro));
    }

    @PostMapping("/_complete")
    @ApiOperation("检索 - 物流公司")
    public Result<List<CompanyComplexVo>> complete(@Validated @RequestBody CompanyCompleteRo ro) {
        return Result.success(service.complete(ro));
    }

}

