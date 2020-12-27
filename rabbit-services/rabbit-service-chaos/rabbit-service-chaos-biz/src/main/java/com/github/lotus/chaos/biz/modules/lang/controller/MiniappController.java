package com.github.lotus.chaos.biz.modules.lang.controller;

import in.hocg.boot.web.result.Result;
import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hocgin on 2020/12/27
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Api(tags = "chaos::小程序")
@RestController
@RequestMapping
public class MiniappController {

    public Result<String> getRecommended() {
        return Result.success();
    }

}
