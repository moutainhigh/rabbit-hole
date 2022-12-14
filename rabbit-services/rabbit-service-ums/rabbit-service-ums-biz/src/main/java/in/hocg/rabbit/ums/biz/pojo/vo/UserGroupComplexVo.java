package in.hocg.rabbit.ums.biz.pojo.vo;

import in.hocg.boot.named.annotation.InjectNamed;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.Collections;
import java.util.List;

/**
 * Created by hocgin on 2021/1/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@InjectNamed
@Accessors(chain = true)
public class UserGroupComplexVo extends UserGroupOrdinaryVo {
    @ApiModelProperty("ๆ้ๅ่กจ")
    private List<AuthorityOrdinaryVo> authorities = Collections.emptyList();
}
