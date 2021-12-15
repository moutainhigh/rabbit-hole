package in.hocg.rabbit.mina.biz.mapstruct;

import in.hocg.rabbit.mina.biz.entity.ProxyChannel;
import in.hocg.rabbit.mina.biz.pojo.ro.ProxyChannelSaveRo;
import in.hocg.rabbit.mina.biz.pojo.vo.ProxyChannelComplexVo;
import in.hocg.rabbit.mina.biz.pojo.vo.ProxyChannelOrdinaryVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2021/11/19
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface ProxyChannelMapping {
    @Mapping(target = "userName", ignore = true)
    @Mapping(target = "lastUpdaterName", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    ProxyChannelOrdinaryVo asProxyChannelOrdinaryVo(ProxyChannel entity);

    ProxyChannelComplexVo asProxyChannelComplexVo(ProxyChannel entity);

    @Mapping(target = "tenantId", ignore = true)
    @Mapping(target = "prefix", ignore = true)
    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleteFlag", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "channelId", ignore = true)
    ProxyChannel asProxyChannel(ProxyChannelSaveRo ro);
}
