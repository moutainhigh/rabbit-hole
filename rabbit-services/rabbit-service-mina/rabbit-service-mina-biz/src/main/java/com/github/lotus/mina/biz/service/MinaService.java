package com.github.lotus.mina.biz.service;

/**
 * Created by hocgin on 2021/1/3
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface MinaService {

    boolean checkMessage(String appid, String text);

    boolean checkImage(String appid, String imageUrl);
}
