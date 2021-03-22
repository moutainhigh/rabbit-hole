package com.github.lotus.com.biz.service.impl;

import com.github.lotus.com.biz.entity.PersonalMessage;
import com.github.lotus.com.biz.mapper.PersonalMessageMapper;
import com.github.lotus.com.biz.service.PersonalMessageService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * [消息模块] 私信消息表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-03-21
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class PersonalMessageServiceImpl extends AbstractServiceImpl<PersonalMessageMapper, PersonalMessage> implements PersonalMessageService {

}
