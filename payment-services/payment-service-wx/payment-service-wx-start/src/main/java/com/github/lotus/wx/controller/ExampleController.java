package com.github.lotus.wx.controller;


import com.github.lotus.usercontext.autoconfigure.UserContextHolder;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

/**
 * <p>
 * 前端控制器
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
    public Object worked() {
        Optional<String> username = UserContextHolder.getUsername();
        // This is sample
        return "This is sample: " + username.orElse(null);
    }
}

