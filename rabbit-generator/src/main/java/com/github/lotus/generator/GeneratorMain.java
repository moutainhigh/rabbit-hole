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
        // com_comment_target com_comment
        CodeGenerator.generateByTables(DataSource.DEFAULT,
            Module.COM, false,
            "com_project", "com_feedback");
    }

}
