package com.github.lotus.com.biz.controller.mina;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.com.biz.pojo.ro.message.MessagePagingRo;
import com.github.lotus.com.biz.pojo.ro.message.SendPersonalMessageRo;
import com.github.lotus.com.biz.pojo.ro.message.SendSystemMessageRo;
import com.github.lotus.com.biz.pojo.vo.message.MessageComplexVo;
import com.github.lotus.com.biz.pojo.vo.message.MessageStatVo;
import com.github.lotus.com.biz.service.MessageUserRefService;
import com.github.lotus.common.utils.RabbitUtils;
import com.github.lotus.usercontext.autoconfigure.UserContextHolder;
import in.hocg.boot.logging.autoconfiguration.core.UseLogger;
import in.hocg.boot.utils.ValidUtils;
import in.hocg.boot.web.result.Result;
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
@RestController
@Api(tags = "com::消息模块")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/mina/message")
public class MinaMessageController {
    private final MessageUserRefService service;

    @UseLogger("分页查询消息 - 我的消息")
    @ApiOperation("分页查询消息 - 我的消息")
    @PostMapping("/_paging")
    public Result<IPage<MessageComplexVo>> paging(@Validated @RequestBody MessagePagingRo ro) {
        ro.setUserId(UserContextHolder.getUserIdThrow());
        return Result.success(service.pagingWithSelf(ro));
    }

    @ApiOperation("获取消息状态 - 我的消息")
    @GetMapping("/stat")
    public Result<MessageStatVo> getMessageStat() {
        return Result.success(UserContextHolder.getUserId()
            .map(service::getMessageStatByUserId).orElse(null));
    }

    @ApiOperation("发送私信消息 - 我的消息")
    @PostMapping("/personal/send")
    public Result<Void> sendPersonalMessage(@Validated @RequestBody SendPersonalMessageRo ro) {
        ro.setUserId(UserContextHolder.getUserIdThrow());
        service.sendPersonalMessage(ro);
        return Result.success();
    }

    @ApiOperation("发送系统消息 - 我的消息")
    @PostMapping("/system/send")
    public Result<Void> sendSystemMessage(@Validated @RequestBody SendSystemMessageRo ro) {
        Long userId = UserContextHolder.getUserIdThrow();
        ValidUtils.isTrue(RabbitUtils.isSuperAdmin(userId));
        ro.setUserId(userId);
        service.sendSystemMessage(ro);
        return Result.success();
    }
}

