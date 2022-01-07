package in.hocg.rabbit.mall.biz.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;

/**
 * Created by hocgin on 2022/1/11
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
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
