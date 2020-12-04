package com.github.lotus.tpl.biz.service.impl;

import com.github.lotus.tpl.biz.entity.Example;
import com.github.lotus.tpl.biz.mapper.ExampleMapper;
import com.github.lotus.tpl.biz.service.ExampleService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2020-08-21
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ExampleServiceImpl extends AbstractServiceImpl<ExampleMapper, Example> implements ExampleService {

}
