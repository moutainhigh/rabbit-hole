package com.github.lotus.com.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.com.biz.pojo.dto.SendNoticeMessageDto;
import com.github.lotus.com.biz.pojo.dto.SendPersonalMessageDto;
import com.github.lotus.com.biz.pojo.dto.SendSystemMessageDto;
import com.github.lotus.com.biz.pojo.ro.message.MessagePagingRo;
import com.github.lotus.com.biz.pojo.vo.message.MessageComplexVo;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by hocgin on 2021/3/21
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface MessageUserRefProxyService {
    MessageComplexVo getById(Long id);

    IPage<MessageComplexVo> paging(MessagePagingRo ro);

    void sendSystemMessage(SendSystemMessageDto dto);

    void sendNoticeMessage(SendNoticeMessageDto dto);

    void sendPersonalMessage(SendPersonalMessageDto dto);
}
