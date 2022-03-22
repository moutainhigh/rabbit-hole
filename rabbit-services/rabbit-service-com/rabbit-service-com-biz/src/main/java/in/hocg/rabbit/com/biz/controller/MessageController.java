package in.hocg.rabbit.com.biz.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.vo.IScroll;
import in.hocg.rabbit.com.biz.pojo.ro.message.*;
import in.hocg.rabbit.com.biz.pojo.vo.message.MessageComplexVo;
import in.hocg.rabbit.com.biz.pojo.vo.message.MessageScrollBySenderVo;
import in.hocg.rabbit.com.biz.pojo.vo.message.MessageStatVo;
import in.hocg.rabbit.com.biz.service.MessageUserRefService;
import in.hocg.rabbit.usercontext.autoconfigure.UserContextHolder;
import in.hocg.boot.logging.autoconfiguration.core.UseLogger;
import in.hocg.boot.utils.struct.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * [消息模块]
 * </p>
 *
 * @author hocgin
 * @since 2021-03-21
 */
@Validated
@RestController
@Api(tags = "com::消息模块")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/message")
public class MessageController {
    private final MessageUserRefService service;

    @UseLogger
    @ApiOperation("接收的消息 - 我的消息")
    @PostMapping("/_paging")
    public Result<IPage<MessageComplexVo>> paging(@RequestBody MessagePagingRo ro) {
        ro.setReceiverUser(UserContextHolder.getUserIdThrow());
        return Result.success(service.pagingWithSelf(ro));
    }

    @UseLogger
    @ApiOperation("接收的消息 - 下拉翻页")
    @PostMapping("/_scroll")
    public Result<IScroll<MessageComplexVo>> scroll(@RequestBody MessageScrollRo ro) {
        ro.setReceiverUser(UserContextHolder.getUserIdThrow());
        return Result.success(service.scroll(ro));
    }

    @UseLogger
    @ApiOperation("最近发送人(私信+未读数量+最近一条消息) - 下拉翻页")
    @PostMapping("/sender/_scroll")
    public Result<IScroll<MessageComplexVo>> scrollBySender(@RequestBody MessageScrollBySenderRo ro) {
        ro.setReceiverUser(UserContextHolder.getUserIdThrow());
        return Result.success(service.scrollBySender(ro));
    }

    @UseLogger
    @ApiOperation("消息状态 - 我的消息")
    @GetMapping("/stat")
    public Result<MessageStatVo> getMessageStat() {
        return Result.success(UserContextHolder.getUserId()
            .map(service::getMessageStatByUserId).orElse(new MessageStatVo()));
    }

    @UseLogger
    @ApiOperation("发送私信消息 - 我的消息")
    @PostMapping("/personal/send")
    public Result<Void> sendPersonalMessage(@RequestBody SendPersonalMessageRo ro) {
        ro.setUserId(UserContextHolder.getUserIdThrow());
        service.sendPersonalMessage(ro);
        return Result.success();
    }

    @UseLogger
    @ApiOperation("发送系统消息 - 我的消息")
    @PostMapping("/system/send")
    public Result<Void> sendSystemMessage(@RequestBody SendSystemMessageRo ro) {
        ro.setUserId(UserContextHolder.getUserIdThrow());
        service.sendSystemMessage(ro);
        return Result.success();
    }
}

