package in.hocg.rabbit.owp.biz.service.impl;

import in.hocg.rabbit.owp.biz.entity.OwpAuthorityApiRef;
import in.hocg.rabbit.owp.biz.mapper.OwpAuthorityApiRefMapper;
import in.hocg.rabbit.owp.biz.service.OwpAuthorityApiRefService;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * [开放平台] 权限x接口表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2022-04-10
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class OwpAuthorityApiRefServiceImpl extends AbstractServiceImpl<OwpAuthorityApiRefMapper, OwpAuthorityApiRef> implements OwpAuthorityApiRefService {

}
