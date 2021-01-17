package com.github.lotus.com.biz.controller;


import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * [通用模块] 项目表 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2021-01-13
 */
@Api(tags = "com::项目")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/project")
public class ProjectController {

}

