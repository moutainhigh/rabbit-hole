package in.hocg.rabbit.owp.biz.service.impl;

import cn.hutool.core.util.StrUtil;
import in.hocg.boot.utils.LangUtils;
import in.hocg.rabbit.owp.api.pojo.vo.ApiRouterVo;
import in.hocg.rabbit.owp.biz.entity.OwpApi;
import in.hocg.rabbit.owp.biz.mapper.OwpApiMapper;
import in.hocg.rabbit.owp.biz.service.OwpApiService;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;

/**
 * <p>
 * [开放平台] 接口表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2022-04-10
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class OwpApiServiceImpl extends AbstractServiceImpl<OwpApiMapper, OwpApi> implements OwpApiService {

    @Override
    public List<ApiRouterVo> listAll() {
        return LangUtils.toList(lambdaQuery().eq(OwpApi::getEnabled, true).list(), api -> new ApiRouterVo()
            .setTitle(api.getTitle())
            .setMapUri(api.getMapUri())
            .setMapPath(api.getMapPath())
            .setEncoding(api.getEncoding()));
    }

    @Override
    public Boolean hasAuthority(String appid, String method) {
        if (StrUtil.isBlank(appid) || StrUtil.isBlank(method)) {
            return false;
        }
        return Objects.nonNull(baseMapper.hasAuthority(appid, method));
    }
}
