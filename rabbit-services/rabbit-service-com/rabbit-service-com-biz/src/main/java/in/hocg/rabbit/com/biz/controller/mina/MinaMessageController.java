package in.hocg.rabbit.com.biz.controller.mina;


import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.rabbit.com.biz.pojo.ro.message.MessagePagingRo;
import in.hocg.rabbit.com.biz.pojo.ro.message.SendPersonalMessageRo;
import in.hocg.rabbit.com.biz.pojo.ro.message.SendSystemMessageRo;
import in.hocg.rabbit.com.biz.pojo.vo.message.MessageComplexVo;
import in.hocg.rabbit.com.biz.pojo.vo.message.MessageStatVo;
import in.hocg.rabbit.com.biz.service.MessageUserRefService;
import in.hocg.rabbit.common.utils.RabbitUtils;
import in.hocg.rabbit.usercontext.autoconfigure.UserContextHolder;
import in.hocg.rabbit.usercontext.ifc.vo.UserDetail;
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
        UserDetail userDetail = UserContextHolder.getUserInfoThrow();
        ValidUtils.isTrue(RabbitUtils.isSuperAdmin(userDetail.getUsername()));
        ro.setUserId(userDetail.getId());
        service.sendSystemMessage(ro);
        return Result.success();
    }
}

