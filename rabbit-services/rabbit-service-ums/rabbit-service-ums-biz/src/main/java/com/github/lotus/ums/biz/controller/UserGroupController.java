package com.github.lotus.ums.biz.controller;


import com.github.lotus.ums.biz.service.UserGroupService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * [权限模块] 用户组表 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2021-01-19
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/user-group")
public class UserGroupController {
    private final UserGroupService service;

}

