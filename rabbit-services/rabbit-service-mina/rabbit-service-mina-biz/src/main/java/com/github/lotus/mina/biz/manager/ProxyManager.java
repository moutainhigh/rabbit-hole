package com.github.lotus.mina.biz.manager;

import com.github.lotus.mina.biz.entity.ProxyChannel;
import com.github.lotus.mina.biz.pojo.vo.ProxyChannelInfoVo;

/**
 * Created by hocgin on 2021/11/12
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface ProxyManager {

    ProxyChannelInfoVo getFrpChannel(ProxyChannel channel);
}
