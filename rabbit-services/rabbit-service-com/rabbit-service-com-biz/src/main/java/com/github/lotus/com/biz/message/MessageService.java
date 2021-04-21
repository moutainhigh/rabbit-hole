package com.github.lotus.com.biz.message;

/**
 * Created by hocgin on 2021/4/21
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface MessageService {

    /**
     * 发送同步消息
     *
     * @param topic   主题
     * @param message 消息
     * @return 是否成功
     */
    boolean syncSend(MessageTopic topic, Object message);
}
