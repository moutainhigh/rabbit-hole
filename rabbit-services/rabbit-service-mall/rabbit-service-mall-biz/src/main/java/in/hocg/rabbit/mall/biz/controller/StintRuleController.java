package in.hocg.rabbit.mall.biz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.boot.logging.autoconfiguration.core.UseLogger;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.ro.DeleteRo;
import in.hocg.boot.utils.struct.result.Result;
import in.hocg.boot.validation.group.Insert;
import in.hocg.boot.validation.group.Update;
import in.hocg.rabbit.mall.biz.pojo.ro.*;
import in.hocg.rabbit.mall.biz.pojo.vo.ProductComplexVo;
import in.hocg.rabbit.mall.biz.pojo.vo.ProductOrdinaryVo;
import in.hocg.rabbit.mall.biz.pojo.vo.StintRuleComplexVo;
import in.hocg.rabbit.mall.biz.pojo.vo.StintRuleOrdinaryVo;
import in.hocg.rabbit.mall.biz.service.StintRuleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by hocgin on 2022/1/19
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Api(tags = "mall::限制规则")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/stint-rule")
public class StintRuleController {
    private final StintRuleService service;


    @UseLogger
    @PostMapping
    @ApiOperation("新增 - 限制规则")
    public Result<Void> insertOne(@Validated(Insert.class) @RequestBody StintRuleSaveRo ro) {
        service.insertOne(ro);
        return Result.success();
    }

    @UseLogger
    @PutMapping("/{id}")
    @ApiOperation("更新 - 限制规则")
    public Result<Void> updateOne(@PathVariable("id") Long id, @Validated(Update.class) @RequestBody StintRuleSaveRo ro) {
        service.updateOne(id, ro);
        return Result.success();
    }

    @UseLogger
    @DeleteMapping
    @ApiOperation("删除 - 限制规则")
    public Result<Void> delete(@Validated @RequestBody DeleteRo ro) {
        service.delete(ro);
        return Result.success();
    }

    @UseLogger
    @GetMapping("/{id}/complex")
    @ApiOperation("查看信息 - 限制规则")
    public Result<StintRuleComplexVo> getComplexById(@PathVariable("id") Long id) {
        return Result.success(service.getComplex(id));
    }

    @UseLogger
    @PostMapping("/_paging")
    @ApiOperation("分页查询 - 限制规则")
    public Result<IPage<StintRuleOrdinaryVo>> paging(@Validated @RequestBody StintRulePagingRo ro) {
        return Result.success(service.paging(ro));
    }

    @UseLogger
    @PostMapping("/_complete")
    @ApiOperation("查询列表 - 限制规则")
    public Result<List<StintRuleOrdinaryVo>> complete(@Validated @RequestBody StintRuleCompleteRo qo) {
        return Result.success(service.complete(qo));
    }
}
