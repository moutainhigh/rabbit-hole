package in.hocg.rabbit.mall.biz.support.helper.order2.modal;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Created by hocgin on 2020/7/14.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GeneralOrder extends AbsOrder<GeneralProduct> {

    private final Long accountId;
    private final String orderSource;
    private final LocalDateTime createAt;

    public GeneralOrder(List<GeneralProduct> products, Long accountId, String orderSource, LocalDateTime createAt) {
        super(products);
        this.accountId = accountId;
        this.orderSource = orderSource;
        this.createAt = createAt;
    }
}
