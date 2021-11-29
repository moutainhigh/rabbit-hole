package com.github.lotus.mina.biz.mapstruct;

import com.github.lotus.mina.biz.entity.ProxyChannel;
import com.github.lotus.mina.biz.pojo.ro.ProxyChannelSaveRo;
import com.github.lotus.mina.biz.pojo.vo.ProxyChannelComplexVo;
import com.github.lotus.mina.biz.pojo.vo.ProxyChannelOrdinaryVo;
import org.mapstruct.Mapper;

/**
 * Created by hocgin on 2021/11/19
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface ProxyChannelMapping {
    ProxyChannelOrdinaryVo asProxyChannelOrdinaryVo(ProxyChannel entity);

    ProxyChannelComplexVo asProxyChannelComplexVo(ProxyChannel entity);

    ProxyChannel asProxyChannel(ProxyChannelSaveRo ro);
}
