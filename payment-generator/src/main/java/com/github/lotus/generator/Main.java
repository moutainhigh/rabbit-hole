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
public class Main {

    public static void main(String[] args) {
        CodeGenerator.generateByTables(DataSource.Chaos,
            Module.Chaos_COM, false,
            "com_data_dict", "com_data_dict_item");
    }

}
