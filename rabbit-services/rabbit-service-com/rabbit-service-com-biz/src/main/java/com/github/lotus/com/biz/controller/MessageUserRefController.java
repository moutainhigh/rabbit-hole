package com.github.lotus.com.biz.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.com.biz.pojo.ro.message.MessagePagingRo;
import com.github.lotus.com.biz.pojo.ro.message.SendPersonalMessageRo;
import com.github.lotus.com.biz.pojo.vo.message.MessageComplexVo;
import com.github.lotus.com.biz.service.MessageUserRefService;
import com.github.lotus.usercontext.autoconfigure.UserContextHolder;
import in.hocg.boot.web.result.Result;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * [消息模块]
 * </p>
 *
 * @author hocgin
 * @since 2021-03-21
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/user/message")
public class MessageUserRefController {
    private final MessageUserRefService service;

    @ApiOperation("分页查询 - 我的消息")
    @PostMapping("/_paging")
    public Result<IPage<MessageComplexVo>> paging(@Validated @RequestBody MessagePagingRo ro) {
        ro.setUserId(UserContextHolder.getUserIdThrow());
        return Result.success(service.pagingWithSelf(ro));
    }

    @ApiOperation("发送私信 - 我的消息")
    @PostMapping("/personal/send")
    public Result<Void> sendPersonalMessage(@Validated @RequestBody SendPersonalMessageRo ro) {
        ro.setUserId(UserContextHolder.getUserIdThrow());
        service.sendPersonalMessage(ro);
        return Result.success();
    }
}

