package com.github.lotus.com.biz.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.com.biz.pojo.ro.ChildCommentPagingRo;
import com.github.lotus.com.biz.pojo.ro.CommentInsertRo;
import com.github.lotus.com.biz.pojo.ro.CommentLikeRo;
import com.github.lotus.com.biz.pojo.ro.CommentPagingRo;
import com.github.lotus.com.biz.pojo.ro.CommentUpdateRo;
import com.github.lotus.com.biz.pojo.ro.RootCommentPagingRo;
import com.github.lotus.com.biz.pojo.vo.CommentComplexVo;
import com.github.lotus.com.biz.pojo.vo.RootCommentComplexVo;
import com.github.lotus.com.biz.service.CommentService;
import com.github.lotus.usercontext.autoconfigure.UserContextHolder;
import in.hocg.boot.logging.autoconfiguration.core.UseLogger;
import in.hocg.boot.web.result.Result;
import io.swagger.annotations.Api;
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
 * [通用模块] 评论表 前端控制器
 * </p>
 *
 * @author hocgin
 * @since 2021-01-13
 */
@Api(tags = "com::评论")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/comment")
public class CommentController {
    private final CommentService service;

    @UseLogger("更新 - 评论")
    @PutMapping("/{id:\\d+}")
    public Result<Void> updateOne(@PathVariable("id") Long id,
                                  @Validated @RequestBody CommentUpdateRo ro) {
        UserContextHolder.getUserId().ifPresent(ro::setUserId);
        service.updateOne(id, ro);
        return Result.success();
    }

    @UseLogger("新增 - 评论")
    @PostMapping
    public Result<Void> insertOne(@Validated @RequestBody CommentInsertRo ro) {
        UserContextHolder.getUserId().ifPresent(ro::setUserId);
        service.insertOne(ro);
        return Result.success();
    }

    @UseLogger("喜欢 - 评论")
    @PostMapping("/{id:\\d+}/like")
    public Result<Void> like(@PathVariable("id") Long id) {
        CommentLikeRo ro = new CommentLikeRo().setId(id);
        UserContextHolder.getUserId().ifPresent(ro::setUserId);
        service.like(ro);
        return Result.success();
    }

    @UseLogger("分页查询(分级模式) - 根评论")
    @PostMapping("/level/_paging")
    public Result<IPage<RootCommentComplexVo>> pagingRootComment(@Validated @RequestBody RootCommentPagingRo ro) {
        return Result.success(service.pagingRootComment(ro));
    }

    @UseLogger("分页查询(分级模式) - 子评论")
    @PostMapping("/level/{parentId:\\d+}/_paging")
    public Result<IPage<CommentComplexVo>> pagingChildComment(@PathVariable("parentId") Long parentId,
                                                              @RequestBody ChildCommentPagingRo ro) {
        ro.setParentId(parentId);
        return Result.success(service.pagingChildComment(ro));
    }

    @UseLogger("分页查询(分层模式) - 层评论")
    @PostMapping("/layer/_paging")
    public Result<IPage<CommentComplexVo>> paging(@Validated @RequestBody CommentPagingRo ro) {
        return Result.success(service.paging(ro));
    }
}

