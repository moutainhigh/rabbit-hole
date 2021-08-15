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
            Module.BMW, false,
            "bmw_access_mch",
            "bmw_account",
            "bmw_account_flow",
            "bmw_pay_record",
            "bmw_pay_scene",
            "bmw_pay_scene_support",
            "bmw_payment_access_ref",
            "bmw_payment_mch",
            "bmw_payment_mch_pay_type",
            "bmw_payment_mch_record",
            "bmw_payment_mch_type",
            "bmw_payment_support",
            "bmw_refund_record",
            "bmw_sync_access_mch_task",
            "bmw_trade_order",
            "");
    }

}
