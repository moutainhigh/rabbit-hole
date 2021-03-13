package com.github.lotus.mina.biz.pojo.ro;

import in.hocg.boot.mybatis.plus.autoconfiguration.ro.CompleteRo;
import lombok.Data;

/**
 * Created by hocgin on 2021/3/13
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class MinaMobileWallpaperCompleteRo extends CompleteRo {
    private String keyword;
    private Boolean enabled;
}
