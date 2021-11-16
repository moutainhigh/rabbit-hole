package com.github.lotus.mina.biz.support.frp.ro;

import lombok.Data;

import java.io.Serializable;

/**
 * Created by hocgin on 2021/11/8
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class FrpOptRo<T> implements Serializable {
    private T content;
}
