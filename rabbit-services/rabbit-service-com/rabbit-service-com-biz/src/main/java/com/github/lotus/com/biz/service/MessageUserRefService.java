package com.github.lotus.com.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.com.biz.entity.MessageUserRef;
import com.github.lotus.com.biz.pojo.ro.message.MessagePagingRo;
import com.github.lotus.com.biz.pojo.vo.message.MessageComplexVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

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

    IPage<MessageUserRef> paging(MessagePagingRo ro);
}
