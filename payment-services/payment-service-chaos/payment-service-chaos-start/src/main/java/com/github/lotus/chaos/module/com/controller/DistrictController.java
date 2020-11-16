package com.github.lotus.chaos.module.com.controller;


import com.github.lotus.chaos.module.com.pojo.vo.district.DistrictComplexVo;
import com.github.lotus.chaos.module.com.pojo.vo.district.DistrictTreeVo;
import com.github.lotus.chaos.module.com.service.DistrictService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
@Api(tags = "地理位置")
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
    public List<DistrictTreeVo> tree() {
        return service.tree();
    }

    @ApiOperation("获取指定下一级区域列表 - 城市区域")
    @GetMapping("/next")
    public List<DistrictComplexVo> getChildrenDistrictByAdcode(@RequestParam(value = "adcode", required = false, defaultValue = "100000") String adcode) {
        return service.getChildrenDistrictByAdcode(adcode);
    }

    @ApiOperation("获取省份列表 - 城市区域")
    @GetMapping("/province")
    public List<DistrictComplexVo> getProvince() {
        return service.getProvince();
    }

    @ApiOperation("获取城市列表 - 城市区域")
    @GetMapping("/city")
    public List<DistrictComplexVo> getCity() {
        return service.getCity();
    }

    @ApiOperation("获取国家列表 - 城市区域")
    @GetMapping("/county")
    public List<DistrictComplexVo> getCounty() {
        return service.getCounty();
    }

    @ApiOperation("获取地区列表 - 城市区域")
    @GetMapping("/district")
    public List<DistrictComplexVo> getDistrict() {
        return service.getDistrict();
    }
}

