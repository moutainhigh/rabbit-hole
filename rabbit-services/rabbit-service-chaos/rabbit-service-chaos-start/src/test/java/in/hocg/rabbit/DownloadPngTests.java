package in.hocg.rabbit;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpUtil;
import com.google.common.collect.Lists;
import org.apache.commons.io.FileUtils;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by hocgin on 2022/2/9
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public class DownloadPngTests {

    public static void main(String[] args) {
        String toPath = "/Users/hocgin/Desktop/图片收藏/头像/all";

        List<String> list = Lists.newArrayList(
            "https://cdn.hellobeebee.com/face/chris/men-11.png",
            "https://cdn.hellobeebee.com/face/chris/men-12.png",
            "https://cdn.hellobeebee.com/face/chris/men-13.png",
            "https://cdn.hellobeebee.com/face/chris/men-9.png",
            "https://cdn.hellobeebee.com/face/chris/men-8.png",
            "https://cdn.hellobeebee.com/face/chris/men-7.png",
            "https://cdn.hellobeebee.com/face/chris/men-6.png",
            "https://cdn.hellobeebee.com/face/chris/men-4.png",
            "https://cdn.hellobeebee.com/face/chris/men-5.png",
            "https://cdn.hellobeebee.com/face/chris/men-1.png",
            "https://cdn.hellobeebee.com/face/chris/men-2.png",
            "https://cdn.hellobeebee.com/face/chris/men-3.png",
            "https://cdn.hellobeebee.com/face/chris/men-10.png",
            "https://cdn.hellobeebee.com/face/chris/plus-12.png",
            "https://cdn.hellobeebee.com/face/chris/plus-13.png",
            "https://cdn.hellobeebee.com/face/chris/plus-11.png",
            "https://cdn.hellobeebee.com/face/chris/plus-10.png",
            "https://cdn.hellobeebee.com/face/chris/plus-7.png",
            "https://cdn.hellobeebee.com/face/chris/plus-8.png",
            "https://cdn.hellobeebee.com/face/chris/plus-9.png",
            "https://cdn.hellobeebee.com/face/chris/plus-5.png",
            "https://cdn.hellobeebee.com/face/chris/plus-6.png",
            "https://cdn.hellobeebee.com/face/chris/plus-3.png",
            "https://cdn.hellobeebee.com/face/chris/plus-4.png",
            "https://cdn.hellobeebee.com/face/chris/plus-1.png",
            "https://cdn.hellobeebee.com/face/chris/plus-2.png"
        );

        // frame
        for (String url : list) {
            String filename = FileUtil.getName(url);
            filename = StrUtil.format("zb_frame__{}_{}", System.currentTimeMillis(), filename);
            HttpUtil.downloadFile(url, Path.of(toPath, filename).toFile());
        }

    }

}
