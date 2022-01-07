package in.hocg.rabbit.mall.biz.pojo.ro;

import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.ro.PageRo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by hocgin on 2022/1/11
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class ShopPagingRo extends PageRo {
    @ApiModelProperty("关键词")
    private String keyword;
}
