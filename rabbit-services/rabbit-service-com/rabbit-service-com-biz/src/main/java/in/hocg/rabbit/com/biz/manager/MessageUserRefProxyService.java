package in.hocg.rabbit.com.biz.manager;

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

    /**
     * 发送系统消息
     *
     * @param dto 消息体
     */
    void sendSystemMessage(SendSystemMessageDto dto);

    /**
     * 发送通知消息
     *
     * @param dto 消息体
     */
    void sendNoticeMessage(SendNoticeMessageDto dto);

    /**
     * 发送个人消息
     *
     * @param dto 消息体
     */
    void sendPersonalMessage(SendPersonalMessageDto dto);

}
