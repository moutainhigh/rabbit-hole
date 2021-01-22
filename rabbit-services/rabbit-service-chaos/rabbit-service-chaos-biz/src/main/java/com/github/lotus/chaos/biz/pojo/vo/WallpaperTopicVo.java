package com.github.lotus.chaos.biz.pojo.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2021/1/17
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class WallpaperTopicVo {
    private String title;
    private String code;
    private String imageUrl;
    private String color;
    private String blurHash;
}
