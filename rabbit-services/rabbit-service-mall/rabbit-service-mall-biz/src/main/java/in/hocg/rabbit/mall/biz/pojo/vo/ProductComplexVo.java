package in.hocg.rabbit.mall.biz.pojo.vo;

import in.hocg.boot.web.datastruct.KeyValue;
import in.hocg.rabbit.chaos.api.named.ChaosNamed;
import in.hocg.rabbit.chaos.api.named.ChaosNamedType;
import in.hocg.rabbit.com.api.pojo.vo.FileVo;
import in.hocg.boot.named.annotation.InjectNamed;
import in.hocg.boot.named.annotation.Named;
import in.hocg.rabbit.mall.api.named.MallNamed;
import in.hocg.rabbit.mall.api.named.MallNamedType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;

/**
 * Created by hocgin on 2020/3/12.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
@InjectNamed
@Accessors(chain = true)
public class ProductComplexVo {
    @ApiModelProperty("ID")
    private Long id;
    @ApiModelProperty("店铺")
    private Long shopId;
    @MallNamed(idFor = "shopId", type = MallNamedType.ShopName)
    private String shopName;
    @ApiModelProperty("标题")
    private String title;
    @ApiModelProperty("属性")
    private List<KeyValue> attrs;
    @ApiModelProperty("品类")
    private Long categoryId;
    @ApiModelProperty("品类名")
    @MallNamed(idFor = "categoryId", type = MallNamedType.ProductCategoryName)
    private String categoryName;

    @ApiModelProperty("视频地址")
    private String videoUrl;
    @ApiModelProperty("产地")
    private String procurement;
    @ApiModelProperty("上架状态")
    private Boolean publishedFlag;
    @ApiModelProperty("单位")
    private String unit;
    private String unitName;
    @ApiModelProperty("商品重量(克)")
    private BigDecimal weight;

    @ApiModelProperty("SKU")
    private List<SkuComplexVo> sku = Collections.emptyList();

    @ApiModelProperty("最低售卖金额")
    private BigDecimal lowestAmt;
    @ApiModelProperty("最高售卖金额")
    private BigDecimal highestAmt;

    @ApiModelProperty("图片列表")
    private List<FileVo> images = Collections.emptyList();

    @ApiModelProperty("主图")
    private String mainImage;

    @ApiModelProperty("创建时间")
    private LocalDateTime createdAt;
    @ApiModelProperty("创建人")
    private Long creator;
    @ChaosNamed(idFor = "creator", type = ChaosNamedType.Userid2Nickname)
    private String creatorName;
    @ApiModelProperty("最后更新时间")
    private LocalDateTime lastUpdatedAt;
    @ApiModelProperty("最后更新时间")
    private Long lastUpdater;
    @ChaosNamed(idFor = "lastUpdater", type = ChaosNamedType.Userid2Nickname)
    private String lastUpdaterName;
}
