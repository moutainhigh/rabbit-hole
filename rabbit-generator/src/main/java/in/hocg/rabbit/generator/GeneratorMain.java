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
            Module.OWP, false,
            "owp_developer",
            "owp_developer_app",
            "owp_api",
            "owp_authority",
            "owp_authority_api_ref",
            "owp_authority_app_ref",
            "");
    }

}
