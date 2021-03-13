package com.github.lotus.mina.biz.pojo.ro;

import in.hocg.boot.mybatis.plus.autoconfiguration.ro.PageRo;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * Created by hocgin on 2021/3/13
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
public class MinaMobileWallpaperPagingRo extends PageRo {
    private String keyword;
    private String enabled;
}
