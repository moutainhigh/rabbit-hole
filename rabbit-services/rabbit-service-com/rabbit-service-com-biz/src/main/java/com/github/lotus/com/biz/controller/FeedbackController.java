package com.github.lotus.com.biz.controller;


import com.github.lotus.com.biz.pojo.ro.FeedbackInsertRo;
import com.github.lotus.com.biz.service.FeedbackService;
import com.github.lotus.usercontext.autoconfigure.UserContextHolder;
import in.hocg.boot.web.autoconfiguration.servlet.SpringServletContext;
import in.hocg.boot.web.autoconfiguration.utils.web.RequestUtils;
import in.hocg.boot.web.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * [通用模块] 用户反馈表 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2021-01-10
 */
@Api(tags = "com::用户反馈")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/feedback")
public class FeedbackController {
    private final FeedbackService service;

    @ApiOperation("用户反馈")
    @PostMapping
    public Result<Void> insertOne(@Validated @RequestBody FeedbackInsertRo ro) {
        ro.setUserId(UserContextHolder.getUserId().orElse(null));
        SpringServletContext.getRequest()
            .ifPresent(request -> ro.setCreatedIp(RequestUtils.getClientIp(request)));
        service.insertOne(ro);
        return Result.success();
    }
}

