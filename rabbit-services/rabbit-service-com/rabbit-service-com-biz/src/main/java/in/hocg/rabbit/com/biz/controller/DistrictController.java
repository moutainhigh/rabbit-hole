package in.hocg.rabbit.com.biz.controller;


import in.hocg.rabbit.com.biz.pojo.ro.district.DistrictCompleteRo;
import in.hocg.rabbit.com.biz.pojo.vo.district.DistrictCompleteVo;
import in.hocg.rabbit.com.api.pojo.vo.DistrictComplexVo;
import in.hocg.rabbit.com.biz.pojo.vo.district.DistrictTreeVo;
import in.hocg.rabbit.com.biz.service.DistrictService;
import com.github.xiaoymin.knife4j.annotations.ApiOperationSupport;
import in.hocg.boot.utils.struct.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * [基础模块] 城市规划表 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2020-11-11
 */
@Api(tags = "com::地理位置")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/district")
public class DistrictController {
    private final DistrictService service;

    @ApiOperation("导入城市区域(高德) - 城市区域")
    @PostMapping("/import-amap")
    public void importByAMap() {
        service.importByAMapUrl();
    }

    @ApiOperation("获取省/市/区(树型) - 城市区域")
    @GetMapping("/tree")
    public Result<List<DistrictTreeVo>> tree() {
        return Result.success(service.tree());
    }

    @ApiOperation("获取指定下一级区域列表 - 城市区域")
    @GetMapping("/next")
    public Result<List<DistrictComplexVo>> getChildrenDistrictByAdcode(@RequestParam(value = "adcode", required = false, defaultValue = "100000") String adcode) {
        return Result.success(service.getChildrenDistrictByAdcode(adcode));
    }

    @ApiOperation("获取省份列表 - 城市区域")
    @GetMapping("/province")
    public Result<List<DistrictComplexVo>> getProvince() {
        return Result.success(service.listProvince());
    }

    @ApiOperation("获取城市列表 - 城市区域")
    @GetMapping("/city")
    public Result<List<DistrictComplexVo>> getCity() {
        return Result.success(service.listCity());
    }

    @ApiOperation("获取国家列表 - 城市区域")
    @GetMapping("/county")
    public Result<List<DistrictComplexVo>> getCounty() {
        return Result.success(service.listCounty());
    }

    @ApiOperation("获取地区列表 - 城市区域")
    @GetMapping("/district")
    public Result<List<DistrictComplexVo>> getDistrict() {
        return Result.success(service.listDistrict());
    }

    @PostMapping("/_complete")
    @ApiOperation("检索 - 城市区域")
    @ApiOperationSupport(author = "hocgin")
    public Result<List<DistrictCompleteVo>> complete(@Validated @RequestBody DistrictCompleteRo ro) {
        return Result.success(service.complete(ro));
    }
}

