package com.github.lotus.com.biz.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * [通用模块] 用户反馈表 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2021-01-10
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/feedback")
public class FeedbackController {

}

