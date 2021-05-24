package com.github.lotus.com.biz.service;

import com.github.lotus.com.biz.pojo.vo.message.NoticeMessageComplexVo;

/**
 * Created by hocgin on 2021/3/21
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface NoticeMessageProxyService {

    /**
     * 解析通知消息
     *
     * @param id _
     * @return _
     */
    NoticeMessageComplexVo getById(Long id);
}
