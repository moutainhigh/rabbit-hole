package in.hocg.rabbit.mall.biz.support.mode.utils;

import lombok.experimental.UtilityClass;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

/**
 * Created by hocgin on 2022/1/13
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class MathUtils {
    public static final MathContext MC = new MathContext(2, RoundingMode.DOWN);

    /**
     * 乘法
     *
     * @param a
     * @param b
     * @return
     */
    public BigDecimal multiply(BigDecimal a, BigDecimal b) {
        return a.multiply(b, MC);
    }

    /**
     * 除法
     *
     * @param a
     * @param b
     * @return
     */
    public BigDecimal divide(BigDecimal a, BigDecimal b) {
        return a.divide(b, MC);
    }

    /**
     * 返回最小值
     *
     * @param a 值1
     * @param b 值2
     * @return 最小值
     */
    public BigDecimal min(BigDecimal a, BigDecimal b) {
        return a.compareTo(b) > 0 ? b : a;
    }

}
