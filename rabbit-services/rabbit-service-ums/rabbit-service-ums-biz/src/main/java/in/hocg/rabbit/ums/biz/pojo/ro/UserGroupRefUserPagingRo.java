package in.hocg.rabbit.ums.biz.pojo.ro;

import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.ro.PageRo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2021/6/12
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@EqualsAndHashCode(callSuper = true)
public class UserGroupRefUserPagingRo extends PageRo {
    @ApiModelProperty("用户组")
    private Long userGroupId;
    @ApiModelProperty("关键字")
    private String keyword;
}
