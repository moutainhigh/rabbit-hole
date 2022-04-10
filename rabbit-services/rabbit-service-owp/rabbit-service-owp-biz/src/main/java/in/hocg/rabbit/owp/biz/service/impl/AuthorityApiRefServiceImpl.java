package in.hocg.rabbit.owp.biz.service.impl;

import in.hocg.rabbit.owp.biz.entity.AuthorityApiRef;
import in.hocg.rabbit.owp.biz.mapper.AuthorityApiRefMapper;
import in.hocg.rabbit.owp.biz.service.AuthorityApiRefService;
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
public class AuthorityApiRefServiceImpl extends AbstractServiceImpl<AuthorityApiRefMapper, AuthorityApiRef> implements AuthorityApiRefService {

}
