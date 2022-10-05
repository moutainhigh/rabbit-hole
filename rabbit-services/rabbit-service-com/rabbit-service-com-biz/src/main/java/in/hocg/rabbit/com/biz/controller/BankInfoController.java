package in.hocg.rabbit.com.biz.controller;


import io.swagger.annotations.Api;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * [通用模块] 银行信息表 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2022-09-22
 */
@Api(tags = "[通用模块] 银行信息表")
@Validated
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/bank-info")
public class BankInfoController {

}

