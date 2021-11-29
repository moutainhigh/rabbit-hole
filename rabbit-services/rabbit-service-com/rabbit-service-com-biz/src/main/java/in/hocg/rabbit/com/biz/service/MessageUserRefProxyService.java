package in.hocg.rabbit.com.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.rabbit.com.biz.pojo.dto.SendNoticeMessageDto;
import in.hocg.rabbit.com.biz.pojo.dto.SendPersonalMessageDto;
import in.hocg.rabbit.com.biz.pojo.dto.SendSystemMessageDto;
import in.hocg.rabbit.com.biz.pojo.ro.message.MessagePagingRo;
import in.hocg.rabbit.com.biz.pojo.vo.message.MessageComplexVo;

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
