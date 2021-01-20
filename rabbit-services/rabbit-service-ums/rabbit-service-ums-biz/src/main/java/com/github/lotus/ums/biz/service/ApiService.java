package com.github.lotus.ums.biz.service;

import com.github.lotus.ums.biz.entity.Api;
import com.github.lotus.ums.biz.pojo.ro.SaveApiRo;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

import java.util.List;

/**
 * <p>
 * [权限模块] 接口表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-19
 */
public interface ApiService extends AbstractService<Api> {

    Long insertOne(SaveApiRo ro);

    void updateOne(Long id, SaveApiRo ro);

    void deleteOne(Long id);

    List<Api> listByUsername(String username);
}
