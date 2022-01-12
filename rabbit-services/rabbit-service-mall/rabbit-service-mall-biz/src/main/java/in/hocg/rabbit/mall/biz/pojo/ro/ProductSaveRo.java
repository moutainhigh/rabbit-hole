package in.hocg.rabbit.mall.biz.pojo.ro;

import com.baomidou.mybatisplus.annotation.TableField;
import in.hocg.boot.validation.group.Insert;
import in.hocg.boot.validation.group.Update;
import in.hocg.boot.web.datastruct.KeyValue;
import in.hocg.rabbit.com.api.pojo.ro.UploadFileRo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

/**
 * Created by hocgin on 2020/3/12.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class ProductSaveRo {
    @ApiModelProperty("店铺")
    @NotNull(groups = {Insert.class}, message = "店铺不能为空")
    private Long shopId;
    @NotBlank(groups = {Insert.class}, message = "商品名称不能为空")
    @ApiModelProperty("商品名称")
    private String title;
    @ApiModelProperty("属性,JSON 格式")
    private List<KeyValue> attrs;
    @ApiModelProperty("采购地")
    private String procurement;
    @ApiModelProperty("图片")
    @Size(groups = {Insert.class, Update.class}, min = 1, message = "图片不能为空")
    @NotNull(groups = {Insert.class}, message = "图片不能为空")
    private List<UploadFileRo.FileDto> images;
    @ApiModelProperty("主视频")
    private String videoUrl;
    @ApiModelProperty("品类ID")
    @NotNull(groups = {Insert.class}, message = "品类不能为空")
    private Long categoryId;
    @NotBlank(groups = {Insert.class, Update.class}, message = "单位错误")
    @ApiModelProperty("单位")
    private String unit;
    @ApiModelProperty("商品重量(克)")
    private BigDecimal weight;
    @NotNull(groups = {Insert.class}, message = "上架状态错误")
    @ApiModelProperty("上架状态")
    private Boolean publishedFlag;
    @Valid
    @NotNull(groups = {Insert.class}, message = "sku不能为空")
    @Size(groups = {Insert.class, Update.class}, min = 1, message = "sku不能为空")
    @ApiModelProperty("SKU列表")
    private List<Sku> sku;

    @Data
    public static class Sku {
        @NotNull(message = "规格不能为空")
        @Size(min = 1, message = "规格不能为空")
        @ApiModelProperty("规格列表")
        private List<Spec> spec;
        @Min(value = 0L, message = "库存数量错误")
        @ApiModelProperty("库存数量")
        private Integer stock;
        @NotBlank(message = "SKU编码不能为空")
        @ApiModelProperty("SKU编码")
        private String encoding;
        @Min(value = 0L, message = "价格错误")
        @ApiModelProperty("价格")
        private BigDecimal unitPrice;
        @ApiModelProperty("图片")
        private String imageUrl;
    }

    @Data
    public static class Spec {
        @NotNull(message = "规格不能为空")
        @ApiModelProperty("规格属性")
        private String key;
        @NotNull(message = "规格值不能为空")
        @ApiModelProperty("规格值")
        private String value;
    }

}
