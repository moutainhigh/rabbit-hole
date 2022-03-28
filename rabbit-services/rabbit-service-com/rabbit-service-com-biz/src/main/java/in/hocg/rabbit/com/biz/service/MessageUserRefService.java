package in.hocg.rabbit.com.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.vo.IScroll;
import in.hocg.rabbit.com.biz.entity.MessageUserRef;
import in.hocg.rabbit.com.biz.pojo.ro.message.*;
import in.hocg.rabbit.com.biz.pojo.vo.message.MessageComplexVo;
import in.hocg.rabbit.com.biz.pojo.vo.message.MessageStatVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractService;

import java.util.List;

/**
 * <p>
 * [消息模块] 用户接收的消息表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-03-21
 */
public interface MessageUserRefService extends AbstractService<MessageUserRef> {

    IPage<MessageComplexVo> pagingWithSelf(MessagePagingRo ro);

    void sendPersonalMessage(SendPersonalMessageRo ro);

    void markReadById(List<Long> ids);

    void sendSystemMessage(SendSystemMessageRo ro);

    MessageStatVo getMessageStatByUserId(Long userId);

    IScroll<MessageComplexVo> scroll(MessageScrollRo ro);

    IScroll<MessageComplexVo> scrollLastByChatUser(MessageByChatUserScrollRo ro);

    IScroll<MessageComplexVo> scrollBySender(MessageByChatUserScrollRo ro);
}
