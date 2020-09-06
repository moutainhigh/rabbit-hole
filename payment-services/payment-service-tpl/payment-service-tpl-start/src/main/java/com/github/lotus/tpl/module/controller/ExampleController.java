package com.github.lotus.tpl.module.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2020-08-21
 */
@Api(tags = "Example 接口")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/example")
public class ExampleController {

    @ApiOperation("测试接口")
    @GetMapping("/worked")
    public Object worked(Principal principal) {
        // This is sample
        return "This is sample: " + principal;
    }
}

