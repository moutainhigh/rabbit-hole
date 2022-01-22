package in.hocg.rabbit.mall.api.enums.orderitem;

import in.hocg.boot.utils.annotation.UseDataDictKey;
import in.hocg.boot.utils.enums.DataDictEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by hocgin on 2022/1/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
@UseDataDictKey(description = "商品类型")
public enum ProductType implements DataDictEnum {
    Sku("sku", "商品规格"),
    ;
    private final Serializable code;
    private final String name;
}
