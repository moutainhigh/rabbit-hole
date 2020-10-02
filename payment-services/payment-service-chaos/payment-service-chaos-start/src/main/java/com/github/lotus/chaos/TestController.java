package com.github.lotus.chaos;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hocgin on 2020/10/2
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Api("测试")
@RequestMapping("/test")
@RestController
public class TestController {

    @ApiOperation("测试接口")
    @GetMapping("/worked")
    @ResponseBody
    public String worked() {
        return "worked";
    }
}
