package in.hocg.rabbit;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import in.hocg.boot.utils.DataDictUtils;
import in.hocg.boot.utils.dto.DictData;
import io.swagger.annotations.ApiOperation;
import lombok.SneakyThrows;
import lombok.experimental.UtilityClass;
import org.apache.commons.io.FileUtils;
import org.springframework.util.ResourceUtils;

import java.io.File;
import java.nio.file.Files;
import java.util.List;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * Created by hocgin on 2021/8/22
 * email: hocgin@gmail.com
 * 功能脚本
 *
 * @author hocgin
 */
@UtilityClass
public class Features {
    public static final Long SUPPER_ADMIN_USER_ID = 1L;

    @SneakyThrows
    @ApiOperation("初始化数据字典")
    public static void main(String[] args) {
        Map<String, Map<String, Object>> maps = DataDictUtils.scanMaps("in.hocg.rabbit");
        List<String> lines = Lists.newArrayList();
        for (Map.Entry<String, Map<String, Object>> entry : maps.entrySet()) {
            lines.add(StrUtil.format("export let {} = {", entry.getKey()));
            entry.getValue().forEach((s, o) -> lines.add(StrUtil.format("    '{}': '{}',", s, o)));
            lines.add("};");
        }
        System.out.println(StrUtil.join("\n", lines));
        File file = ResourceUtils.getFile("docs/ts/all-keys.ts");
        FileUtil.mkParentDirs(file);
        FileUtil.touch(file);
        Files.write(file.toPath(), lines);
    }
}
