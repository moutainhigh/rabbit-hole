package com.github.lotus.com.biz.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * [消息模块] 私信消息表 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2021-03-21
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/personal-message")
public class PersonalMessageController {

}

