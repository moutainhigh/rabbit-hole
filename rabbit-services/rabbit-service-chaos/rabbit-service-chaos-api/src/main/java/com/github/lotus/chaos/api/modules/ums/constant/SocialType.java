package com.github.lotus.chaos.api.modules.ums.constant;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by hocgin on 2020/12/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
public enum SocialType {
    WxMp("wxmp", "微信公众号");
    private final Serializable code;
    private final String name;
}
