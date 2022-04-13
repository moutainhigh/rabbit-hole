package in.hocg.rabbit.owp.biz.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.boot.utils.struct.result.Result;
import in.hocg.rabbit.usercontext.autoconfigure.UserContextHolder;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * [开放平台] 开发者档案表 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2022-04-10
 */
@Validated
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/developer")
public class DeveloperController {

}

