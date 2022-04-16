package in.hocg.rabbit.rcm.biz.controller;


import in.hocg.boot.utils.struct.result.Result;
import in.hocg.rabbit.rcm.biz.pojo.vo.PostCategoryOrdinaryVo;
import in.hocg.rabbit.rcm.biz.service.PostCategoryService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * [内容模块] 帖文类目表 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2022-04-16
 */
@Api(tags = "帖文类目")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/post-category")
public class PostCategoryController {
    private final PostCategoryService service;

    @ApiOperation("主要 - 帖文类目")
    @GetMapping("/main")
    public Result<List<PostCategoryOrdinaryVo>> main() {
        return Result.success(service.listMain());
    }
}

