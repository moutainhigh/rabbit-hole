package in.hocg.rabbit.mina.biz.docking.recharge;

import in.hocg.boot.test.autoconfiguration.core.AbstractSpringBootTest;
import in.hocg.rabbit.chaos.BootApplication;
import in.hocg.rabbit.mina.biz.docking.recharge.pojo.ro.CheckRechargeRo;
import in.hocg.rabbit.mina.biz.docking.recharge.pojo.ro.QueryElecityRo;
import in.hocg.rabbit.mina.biz.docking.recharge.pojo.ro.QueryProductRo;
import in.hocg.rabbit.mina.biz.docking.recharge.pojo.ro.RechargeRo;
import in.hocg.rabbit.mina.biz.docking.recharge.pojo.vo.CheckRechargeVo;
import in.hocg.rabbit.mina.biz.docking.recharge.pojo.vo.ElecityVo;
import in.hocg.rabbit.mina.biz.docking.recharge.pojo.vo.ProductVo;
import in.hocg.rabbit.mina.biz.docking.recharge.pojo.vo.RechargeVo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Created by hocgin on 2022/4/6
 * email: hocgin@gmail.com
 * 商户资料后台地址：http://gzh.beehost.cn/agent.php
 * 登录账号：mjsh
 * 登录密码：egzosn.com
 * 接口地址：http://gzh.beehost.cn/yrapi.php
 * 商户ID：531
 * ApiKey：34cxZ8dULFaz7NkhCrpM2qbJSnA9QGyw
 * <p>
 * 接口文档：https://gitee.com/gate-assembly/api-docking-parameters/blob/master/README.md
 *
 * @author hocgin
 */
@ActiveProfiles("local")
@SpringBootTest(classes = {BootApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class RechargeDockingServiceTest extends AbstractSpringBootTest {
    @Autowired
    RechargeDockingService service;

    @Test
    void checkRecharge() {
        CheckRechargeRo ro = new CheckRechargeRo();
        ro.setOutTradeNums("4567sdhjkl");
        List<CheckRechargeVo> result = service.checkRecharge(ro);
        System.out.println(result);
    }

    @Test
    void listElecity() {
        QueryElecityRo ro = new QueryElecityRo();
        List<ElecityVo> result = service.listElecity(ro);
        System.out.println(result);
    }

    @Test
    void listProduct() {
        QueryProductRo ro = new QueryProductRo();
        List<ProductVo> result = service.listProduct(ro);
        System.out.println(result);
    }

    @Test
    void recharge() {
        RechargeRo ro = new RechargeRo();
        ro.setOutTradeNum("4567sdhjkl");
        ro.setProductId("69");
        ro.setMobile("13600747016");
        ro.setNotifyUrl("https://hocgin.forward.hocgin.top");
        ro.setAmount(30);
        ro.setPrice(30);
        RechargeVo result = service.recharge(ro);
        System.out.println(result);
    }
}
