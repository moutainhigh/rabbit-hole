package com.github.lotus.pay.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.pay.api.pojo.vo.AccessAppOrdinaryVo;
import com.github.lotus.pay.biz.entity.AccessApp;
import com.github.lotus.pay.biz.pojo.ro.AccessAppCompleteRo;
import com.github.lotus.pay.biz.pojo.ro.AccessAppInsertRo;
import com.github.lotus.pay.biz.pojo.ro.AccessAppPagingRo;
import com.github.lotus.pay.biz.pojo.vo.AccessAppComplexVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * [支付网关] 接入应用表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-30
 */
public interface AccessAppService extends AbstractService<AccessApp> {

    Optional<AccessApp> getByEncoding(String encoding);

    void sendAsyncNotifyApp(Long notifyAccessAppId);

    void insertOne(AccessAppInsertRo ro);

    IPage<AccessAppComplexVo> paging(AccessAppPagingRo ro);

    AccessAppComplexVo getComplex(Long id);

    List<AccessAppComplexVo> complete(AccessAppCompleteRo ro);

    void deleteOne(Long id);

    List<AccessAppOrdinaryVo> listOrdinaryById(List<Long> id);
}
