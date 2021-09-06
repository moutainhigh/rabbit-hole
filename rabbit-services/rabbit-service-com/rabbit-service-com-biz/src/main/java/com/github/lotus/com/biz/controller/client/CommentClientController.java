package com.github.lotus.com.biz.controller.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.com.biz.pojo.ro.CommentClientPagingRo;
import com.github.lotus.com.biz.pojo.ro.CommentClientRo;
import com.github.lotus.com.biz.pojo.ro.CommentDislikeRo;
import com.github.lotus.com.biz.pojo.ro.CommentLikeRo;
import com.github.lotus.com.biz.pojo.vo.CommentClientVo;
import com.github.lotus.com.biz.service.CommentService;
import com.github.lotus.usercontext.autoconfigure.UserContextHolder;
import in.hocg.boot.web.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.models.auth.In;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * Created by hocgin on 2021/8/29
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Api(tags = "com::评论(Client)")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/client/comment/{refType}/{refId}")
public class CommentClientController {
    private final CommentService service;

    @ApiOperation("评论")
    @PostMapping("/comment")
    public Result<CommentClientVo> comment(@PathVariable("refType") String refType, @PathVariable("refId") Long refId,
                                           @Validated @RequestBody CommentClientRo ro) {
        ro.setRefType(refType);
        ro.setRefId(refId);
        ro.setUserId(UserContextHolder.getUserIdThrow());
        return Result.success(service.commentWithClient(ro));
    }

    @ApiOperation("顶级评论 - 分页查询")
    @PostMapping("/_paging")
    public Result<IPage<CommentClientVo>> paging(@PathVariable("refType") String refType, @PathVariable("refId") Long refId,
                                                 @Validated @RequestBody CommentClientPagingRo ro) {
        ro.setRefType(refType);
        ro.setRefId(refId);
        UserContextHolder.getUserId().ifPresent(ro::setUserId);
        return Result.success(service.pagingWithClient(ro));
    }

    @ApiOperation("子级评论 - 分页查询")
    @PostMapping("/comment/{parentId}/_paging")
    public Result<IPage<CommentClientVo>> pagingByParentId(@PathVariable("refType") String refType, @PathVariable("refId") Long refId, @PathVariable("parentId") Long parentId,
                                                           @Validated @RequestBody CommentClientPagingRo ro) {
        ro.setParentId(parentId);
        ro.setRefType(refType);
        ro.setRefId(refId);
        UserContextHolder.getUserId().ifPresent(ro::setUserId);
        return Result.success(service.pagingWithClient(ro));
    }

    @ApiOperation("点赞")
    @PostMapping("/{id}/like")
    public Result<Void> like(@PathVariable("id") Long id) {
        CommentLikeRo ro = new CommentLikeRo();
        ro.setId(id);
        ro.setUserId(UserContextHolder.getUserIdThrow());
        service.like(ro);
        return Result.success();
    }

    @ApiOperation("倒赞")
    @PostMapping("/{id}/dislike")
    public Result<Void> dislike(@PathVariable("id") Long id) {
        CommentDislikeRo ro = new CommentDislikeRo();
        ro.setId(id);
        ro.setUserId(UserContextHolder.getUserIdThrow());
        service.dislike(ro);
        return Result.success();
    }
}
