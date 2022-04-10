package in.hocg.rabbit.owp.biz.service.impl;

import in.hocg.boot.mybatis.plus.autoconfiguration.core.enhance.convert.UseConvert;
import in.hocg.rabbit.owp.api.pojo.vo.DevAppVo;
import in.hocg.rabbit.owp.biz.convert.OwpConvert;
import in.hocg.rabbit.owp.biz.entity.DeveloperApp;
import in.hocg.rabbit.owp.biz.mapper.DeveloperAppMapper;
import in.hocg.rabbit.owp.biz.service.DeveloperAppService;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

/**
 * <p>
 * [开放平台] 开发者应用表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2022-04-10
 */
@Service
@UseConvert(OwpConvert.class)
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class DeveloperAppServiceImpl extends AbstractServiceImpl<DeveloperAppMapper, DeveloperApp> implements DeveloperAppService {

    @Override
    public DevAppVo getByEncoding(String encoding) {
        return as(baseMapper.getByEncoding(encoding), DevAppVo.class);
    }
}
