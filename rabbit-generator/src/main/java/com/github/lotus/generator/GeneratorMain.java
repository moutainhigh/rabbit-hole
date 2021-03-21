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
            Module.COM, false,
            "mms_message_user_ref",
            "mms_message_user_ref",
            "mms_system_message",
            "mms_personal_message",
            "mms_notice_message",
            "mms_notice_user_config");
    }

}
