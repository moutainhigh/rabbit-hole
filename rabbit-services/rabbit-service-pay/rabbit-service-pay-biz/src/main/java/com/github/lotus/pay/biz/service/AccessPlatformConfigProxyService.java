package com.github.lotus.pay.biz.service;

import com.baomidou.mybatisplus.extension.activerecord.Model;
import com.github.lotus.pay.biz.pojo.ro.AccessPlatformInsertRo;

/**
 * Created by hocgin on 2021/2/2
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface AccessPlatformConfigProxyService {

    Long insertOne(AccessPlatformInsertRo ro);

    void removeByRefTypeAndRefId(String refType, Long refId);

    Model<?> getByRefTypeAndRefId(String refType, Long refId);
}
