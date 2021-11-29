package in.hocg.rabbit.docking.biz.pojo.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2020/12/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
@Accessors(chain = true)
public class WxMaUserInfoVo {
    private String nickName;
    private String gender;
}
