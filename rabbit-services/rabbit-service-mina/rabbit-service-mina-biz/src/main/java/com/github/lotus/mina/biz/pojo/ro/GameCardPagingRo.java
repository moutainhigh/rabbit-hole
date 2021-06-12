package com.github.lotus.mina.biz.pojo.ro;

import in.hocg.boot.mybatis.plus.autoconfiguration.ro.PageRo;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;


/**
 * Created by hocgin on 2021/2/3
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class GameCardPagingRo extends PageRo {
    private String keyword;
    private String type;
}
