package in.hocg.rabbit.mall.biz.pojo.ro;

import com.baomidou.mybatisplus.annotation.TableField;
import in.hocg.boot.validation.group.Insert;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * Created by hocgin on 2022/1/11
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class ShopSaveRo {
    @NotNull(groups = {Insert.class}, message = "店铺编号不能为空")
    @ApiModelProperty("编码")
    private String encoding;
    @NotNull(groups = {Insert.class}, message = "店铺名称不能为空")
    @ApiModelProperty("店铺名称")
    private String title;
    @ApiModelProperty("备注")
    private String remark;
}
