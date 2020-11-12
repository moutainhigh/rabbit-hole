package com.github.lotus.chaos.module.wl.controller;


import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * [物流模块] 公司表 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2020-11-12
 */
@Api(tags = "物流公司")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/company")
public class CompanyController {

}

