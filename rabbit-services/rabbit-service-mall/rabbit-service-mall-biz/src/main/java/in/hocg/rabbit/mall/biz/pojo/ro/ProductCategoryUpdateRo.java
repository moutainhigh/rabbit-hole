package in.hocg.rabbit.mall.biz.pojo.ro;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * Created by hocgin on 2021/8/21
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class ProductCategoryUpdateRo {
    @ApiModelProperty("父级")
    private Long parentId;
    @NotNull
    @ApiModelProperty("商品品类名称")
    private String title;
    @ApiModelProperty("商品品类描述")
    private String remark;
    @ApiModelProperty("商品品类图片")
    private String imageUrl;
    @ApiModelProperty("商品品类关键词")
    private List<String> keywords;
    @ApiModelProperty("启用状态")
    private Boolean enabled;
    @ApiModelProperty("排序")
    private Integer sort;
}
