package in.hocg.rabbit.com.biz.controller.client;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.vo.IScroll;
import in.hocg.rabbit.com.biz.pojo.ro.CommentClientPagingRo;
import in.hocg.rabbit.com.biz.pojo.ro.CommentClientRo;
import in.hocg.rabbit.com.biz.pojo.ro.CommentDislikeRo;
import in.hocg.rabbit.com.biz.pojo.ro.CommentLikeRo;
import in.hocg.rabbit.com.biz.pojo.ro.comment.CommentClientScrollRo;
import in.hocg.rabbit.com.biz.pojo.ro.comment.CommentReportRo;
import in.hocg.rabbit.com.biz.pojo.vo.CommentClientVo;
import in.hocg.rabbit.com.biz.service.CommentService;
import in.hocg.rabbit.usercontext.autoconfigure.UserContextHolder;
import in.hocg.boot.utils.struct.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
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
@Validated
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/comment/{refType}/{refId}")
public class CommentClientController {
    private final CommentService service;

    @ApiOperation("评论")
    @PostMapping("/reply")
    public Result<CommentClientVo> reply(@PathVariable("refType") String refType, @PathVariable("refId") Long refId,
                                         @RequestBody CommentClientRo ro) {
        ro.setRefType(refType);
        ro.setRefId(refId);
        ro.setOptUserId(UserContextHolder.getUserIdThrow());
        return Result.success(service.replyWithClient(ro));
    }

    @ApiOperation("顶级评论 - 下拉翻页")
    @PostMapping("/_scroll")
    public Result<IScroll<CommentClientVo>> scroll(@PathVariable("refType") String refType, @PathVariable("refId") Long refId,
                                                   @RequestBody CommentClientScrollRo ro) {
        ro.setRefType(refType);
        ro.setRefId(refId);
        UserContextHolder.getUserId().ifPresent(ro::setUserId);
        return Result.success(service.scrollWithClient(ro));
    }

    @ApiOperation("子级评论 - 分页查询")
    @PostMapping("/_paging")
    public Result<IPage<CommentClientVo>> pagingByParentId(@PathVariable("refType") String refType, @PathVariable("refId") Long refId,
                                                           @RequestBody CommentClientPagingRo ro) {
        ro.setRefType(refType);
        ro.setRefId(refId);
        UserContextHolder.getUserId().ifPresent(ro::setUserId);
        return Result.success(service.pagingWithClient(ro));
    }

    @ApiOperation("点赞")
    @PostMapping("/like")
    public Result<CommentClientVo> like(@RequestBody CommentLikeRo ro) {
        ro.setUserId(UserContextHolder.getUserIdThrow());
        return Result.success(service.like(ro));
    }

    @ApiOperation("倒赞")
    @PostMapping("/dislike")
    public Result<CommentClientVo> dislike(@RequestBody CommentDislikeRo ro) {
        ro.setUserId(UserContextHolder.getUserIdThrow());
        return Result.success(service.dislike(ro));
    }

    @ApiOperation("举报")
    @PostMapping("/report")
    public Result<Void> report(@RequestBody CommentReportRo ro) {
        ro.setUserId(UserContextHolder.getUserIdThrow());
        service.report(ro);
        return Result.success();
    }


}
