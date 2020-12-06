package com.github.lotus.docking.api.pojo.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2020/12/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class WxMpQrCodeVo {
    private String qrCodeUrl;
    private String idFlag;
}
