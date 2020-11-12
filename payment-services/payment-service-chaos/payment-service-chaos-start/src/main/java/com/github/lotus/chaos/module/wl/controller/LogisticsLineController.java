package com.github.lotus.chaos.module.wl.controller;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * [物流模块] 物流线路表 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2020-11-12
 */
@Api(tags = "物流线路")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/logistics-line")
public class LogisticsLineController {

}

