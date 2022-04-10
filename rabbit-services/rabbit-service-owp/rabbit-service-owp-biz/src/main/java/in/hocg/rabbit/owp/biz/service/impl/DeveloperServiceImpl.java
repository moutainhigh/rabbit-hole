package in.hocg.rabbit.owp.biz.service.impl;

import in.hocg.rabbit.owp.biz.entity.Developer;
import in.hocg.rabbit.owp.biz.mapper.DeveloperMapper;
import in.hocg.rabbit.owp.biz.service.DeveloperService;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * [开放平台] 开发者档案表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2022-04-10
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class DeveloperServiceImpl extends AbstractServiceImpl<DeveloperMapper, Developer> implements DeveloperService {

}
