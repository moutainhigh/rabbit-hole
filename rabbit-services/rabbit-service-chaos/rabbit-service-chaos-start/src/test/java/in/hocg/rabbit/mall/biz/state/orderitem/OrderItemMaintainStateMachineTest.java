package in.hocg.rabbit.mall.biz.state.orderitem;

import in.hocg.boot.test.autoconfiguration.core.AbstractSpringBootTest;
import in.hocg.rabbit.chaos.BootApplication;
import in.hocg.rabbit.mall.biz.service.OrderItemService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

/**
 * Created by hocgin on 2022/1/15
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@ActiveProfiles("local")
@SpringBootTest(classes = {BootApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderItemMaintainStateMachineTest extends AbstractSpringBootTest {

    @Autowired
    OrderItemService orderItemService;

    @Test
    void start() throws Exception {
    }

    @Test
    void apply() {
    }
}
