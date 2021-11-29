package in.hocg.rabbit.mall.biz.pojo.ro;

import in.hocg.boot.mybatis.plus.autoconfiguration.ro.PageRo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by hocgin on 2020/3/14.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class ProductPagingRo extends PageRo {
    @ApiModelProperty("发布状态")
    private Integer publishStatus;
    private String keyword;
}
