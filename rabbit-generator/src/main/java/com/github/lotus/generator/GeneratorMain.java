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
            Module.UMS, false,
            "ams_role",
            "ams_role_authority_ref",
            "ams_role_user_ref",
            "ams_api",
            "ams_authority_api_ref",
            "ams_user_group",
            "ams_user_group_user_ref",
            "ams_user_group_authority_ref",
            "ams_authority");
    }

}
