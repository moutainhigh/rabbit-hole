package in.hocg.rabbit.mall.biz.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import in.hocg.boot.named.annotation.InjectNamed;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Created by hocgin on 2022/1/11
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
@InjectNamed
@Accessors(chain = true)
public class ShopOrdinaryVo implements Serializable {
    @ApiModelProperty("ID")
    private Long id;
    @ApiModelProperty("编码")
    private String encoding;
    @ApiModelProperty("店铺名称")
    private String title;
    @ApiModelProperty("备注")
    private String remark;
}
