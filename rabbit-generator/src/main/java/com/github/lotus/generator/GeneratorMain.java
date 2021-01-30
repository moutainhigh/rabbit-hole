package com.github.lotus.generator;


import com.github.lotus.generator.core.CodeGenerator;
import com.github.lotus.generator.core.DataSource;
import com.github.lotus.generator.core.Module;

/**
 * Created by hocgin on 2020/5/29.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class GeneratorMain {

    public static void main(String[] args) {
        //
        CodeGenerator.generateByTables(DataSource.DEFAULT,
            Module.PAY, false,
            "bmw_platform_alipay_config",
            "bmw_platform_wxpay_config",
            "bmw_notify_access_app_log",
            "bmw_request_platform_log",
            "bmw_access_app",
            "bmw_access_platform",
            "bmw_notify_access_app",
            "bmw_trade",
            "bmw_pay_record",
            "bmw_refund_record",
            "bmw_payment_platform",
            "bmw_payment_mode");
    }

}
