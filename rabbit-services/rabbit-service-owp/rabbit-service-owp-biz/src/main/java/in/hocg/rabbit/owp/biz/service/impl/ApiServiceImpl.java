package in.hocg.rabbit.owp.biz.service.impl;

import cn.hutool.core.util.StrUtil;
import in.hocg.boot.utils.LangUtils;
import in.hocg.rabbit.owp.api.pojo.vo.ApiRouterVo;
import in.hocg.rabbit.owp.biz.entity.Api;
import in.hocg.rabbit.owp.biz.mapper.ApiMapper;
import in.hocg.rabbit.owp.biz.service.ApiService;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Objects;
import java.util.function.Function;

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
public class ApiServiceImpl extends AbstractServiceImpl<ApiMapper, Api> implements ApiService {

    @Override
    public List<ApiRouterVo> listAll() {
        return LangUtils.toList(lambdaQuery().eq(Api::getEnabled, true).list(), api -> new ApiRouterVo()
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
