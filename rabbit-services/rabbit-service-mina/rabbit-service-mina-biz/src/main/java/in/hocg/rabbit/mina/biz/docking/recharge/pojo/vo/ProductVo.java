package in.hocg.rabbit.mina.biz.docking.recharge.pojo.vo;

import com.alibaba.fastjson.annotation.JSONField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.util.List;

/**
 * Created by hocgin on 2022/4/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class ProductVo {
    @ApiModelProperty("分类ID")
    private Integer id;
    @ApiModelProperty("分类名称")
    private String cate;
    @ApiModelProperty("排序")
    private String sort;
    @ApiModelProperty("产品类型ID")
    private String type;
    @ApiModelProperty("产品列表")
    private List<Product> products;

    @Data
    @Accessors(chain = true)
    public static class Product {
        @ApiModelProperty("产品ID,下单报文中用此参数")
        private String id;
        @ApiModelProperty("产品名称")
        private String name;
        @ApiModelProperty("产品说明")
        private String desc;
        @ApiModelProperty("自动充值")
        @JSONField(name = "api_open")
        private String apiOpen;
        @ApiModelProperty("运营商集合（话费、流量有效），1移动,2电信,3联通,4虚拟")
        @JSONField(name = "isp")
        private String isp;
        @ApiModelProperty("价格，下单扣费金额")
        @JSONField(name = "price")
        private BigDecimal price;
        @ApiModelProperty("原价")
        @JSONField(name = "y_price")
        private BigDecimal yPrice;
        @ApiModelProperty("封顶价格")
        @JSONField(name = "max_price")
        private BigDecimal maxPrice;

        @ApiModelProperty("标签")
        @JSONField(name = "ys_tag")
        private String ysTag;
        @ApiModelProperty("产品类型ID")
        @JSONField(name = "type")
        private String type;
        @ApiModelProperty("产品分类名称")
        @JSONField(name = "cate_name")
        private String cateName;
        @ApiModelProperty("产品类型名称")
        @JSONField(name = "type_name")
        private String typeName;
    }
}
