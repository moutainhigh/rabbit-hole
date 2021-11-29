package in.hocg.rabbit.bmw.api.pojo.ro;

import lombok.Data;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotBlank;

/**
 * Created by hocgin on 2021/8/14
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public abstract class AccessRo {
    @NotBlank(message = "接入商户编号错误")
    private String accessCode;
}
