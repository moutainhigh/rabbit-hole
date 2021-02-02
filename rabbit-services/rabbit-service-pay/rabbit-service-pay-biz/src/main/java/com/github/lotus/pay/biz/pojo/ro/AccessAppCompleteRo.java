package com.github.lotus.pay.biz.pojo.ro;

import in.hocg.boot.mybatis.plus.autoconfiguration.ro.CompleteRo;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * Created by hocgin on 2021/2/2
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
public class AccessAppCompleteRo extends CompleteRo {
    private String keyword;
}
