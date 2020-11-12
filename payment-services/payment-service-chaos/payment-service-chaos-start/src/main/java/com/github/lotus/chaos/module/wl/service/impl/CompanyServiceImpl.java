package com.github.lotus.chaos.module.wl.service.impl;

import com.github.lotus.chaos.module.wl.entity.Company;
import com.github.lotus.chaos.module.wl.mapper.CompanyMapper;
import com.github.lotus.chaos.module.wl.service.CompanyService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 * [物流模块] 公司表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2020-11-12
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CompanyServiceImpl extends AbstractServiceImpl<CompanyMapper, Company> implements CompanyService {

}
