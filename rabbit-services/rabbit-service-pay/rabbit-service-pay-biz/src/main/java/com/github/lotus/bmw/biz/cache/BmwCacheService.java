package com.github.lotus.bmw.biz.cache;

import com.github.lotus.bmw.biz.entity.AccessMch;

/**
 * Created by hocgin on 2021/8/14
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface BmwCacheService {

    AccessMch getAccessMchByEncoding(String encoding);
}
