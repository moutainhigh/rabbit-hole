package com.github.lotus.com.biz.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.com.biz.pojo.ro.ShortUrlInsertRo;
import com.github.lotus.com.biz.pojo.ro.ShortUrlPagingRo;
import com.github.lotus.com.biz.pojo.ro.ShortUrlUpdateRo;
import com.github.lotus.com.biz.pojo.vo.ShortUrlComplexVo;
import com.github.lotus.com.biz.service.ShortUrlService;
import com.github.lotus.usercontext.autoconfigure.UserContextHolder;
import in.hocg.boot.logging.autoconfiguration.core.UseLogger;
import in.hocg.boot.web.result.Result;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * [基础模块] 短链接表 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2021-01-14
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/short-url")
public class ShortUrlController {
    private final ShortUrlService service;

    @UseLogger("分页查询 - 短链接")
    @PostMapping({"/_paging"})
    public Result<IPage<ShortUrlComplexVo>> paging(@Validated @RequestBody ShortUrlPagingRo qo) {
        return Result.success(service.paging(qo));
    }

    @PostMapping
    @UseLogger("新增 - 短链接")
    public Result<Void> insertOne(@Validated @RequestBody ShortUrlInsertRo ro) {
        UserContextHolder.getUserId().ifPresent(ro::setUserId);
        service.insertOne(ro);
        return Result.success();
    }

    @PutMapping("/{id:\\d+}")
    @UseLogger("修改 - 短链接")
    public Result<Void> updateOne(@PathVariable Long id, @Validated @RequestBody ShortUrlUpdateRo ro) {
        UserContextHolder.getUserId().ifPresent(ro::setUserId);
        service.updateOne(id, ro);
        return Result.success();
    }
}

