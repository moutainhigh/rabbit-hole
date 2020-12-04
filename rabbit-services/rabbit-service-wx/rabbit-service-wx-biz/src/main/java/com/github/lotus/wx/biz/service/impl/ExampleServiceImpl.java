package com.github.lotus.wx.biz.service.impl;

import com.github.lotus.wx.biz.entity.Example;
import com.github.lotus.wx.biz.mapper.ExampleMapper;
import com.github.lotus.wx.biz.service.ExampleService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

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
public class ExampleServiceImpl extends AbstractServiceImpl<ExampleMapper, Example>
    implements ExampleService {

}
