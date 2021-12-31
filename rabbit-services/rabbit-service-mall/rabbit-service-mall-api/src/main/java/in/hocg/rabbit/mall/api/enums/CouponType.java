package in.hocg.rabbit.mall.api.enums;

import in.hocg.boot.utils.enums.DataDictEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by hocgin on 2021/8/22
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
public enum CouponType implements DataDictEnum {
    FixedAmt("fixed_amt", "满减"),
    ScaleAmt("scale_amt", "折扣"),
    ;
    private final Serializable code;
    private final String name;

}

