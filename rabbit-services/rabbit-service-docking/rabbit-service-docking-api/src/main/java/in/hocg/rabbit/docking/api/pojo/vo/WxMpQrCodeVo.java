package in.hocg.rabbit.docking.api.pojo.vo;

import io.swagger.annotations.ApiModelProperty;
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
    @ApiModelProperty("二维码地址")
    private String qrcodeUrl;
    @ApiModelProperty("登陆标记")
    private String idFlag;
}
