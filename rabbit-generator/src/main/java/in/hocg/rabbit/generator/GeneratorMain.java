package in.hocg.rabbit.generator;


import in.hocg.rabbit.generator.core.CodeGenerator;
import in.hocg.rabbit.generator.core.DataSource;
import in.hocg.rabbit.generator.core.Module;

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
            Module.RCM, false,
            "rcm_post_classify",
            "rcm_post",
            "");
    }

}
