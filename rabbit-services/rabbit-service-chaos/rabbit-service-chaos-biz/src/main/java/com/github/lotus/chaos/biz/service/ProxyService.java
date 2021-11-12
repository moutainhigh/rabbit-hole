package com.github.lotus.chaos.biz.service;

import com.github.lotus.chaos.biz.pojo.vo.ProxyChannelInfoVo;

/**
 * Created by hocgin on 2021/11/12
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface ProxyService {

    ProxyChannelInfoVo getChannelInfo(String channelId);

}
