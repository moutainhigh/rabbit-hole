package in.hocg.rabbit.mall.biz.pojo.ro;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * Created by hocgin on 2022/1/15
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class PayResultRo {
    private LocalDateTime payAt;
    private String payWay;
}
