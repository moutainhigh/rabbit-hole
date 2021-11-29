package in.hocg.rabbit.mina.biz.mapstruct;

import in.hocg.rabbit.mina.biz.entity.ProxyChannel;
import in.hocg.rabbit.mina.biz.pojo.ro.ProxyChannelSaveRo;
import in.hocg.rabbit.mina.biz.pojo.vo.ProxyChannelComplexVo;
import in.hocg.rabbit.mina.biz.pojo.vo.ProxyChannelOrdinaryVo;
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
