package com.github.lotus.ums.biz.pojo.ro;

import in.hocg.boot.mybatis.plus.autoconfiguration.ro.CompleteRo;
import lombok.Data;

/**
 * Created by hocgin on 2021/1/23
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class UserCompleteRo extends CompleteRo {
    private String keyword;

}
