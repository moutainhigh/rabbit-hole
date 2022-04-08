package in.hocg.rabbit.mina.biz.pojo.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.math.BigDecimal;

/**
 * Created by hocgin on 2022/4/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class RechargeAccountVo {
    private String userId;
    private String apikey;
    private BigDecimal availAmt = BigDecimal.ZERO;
}
