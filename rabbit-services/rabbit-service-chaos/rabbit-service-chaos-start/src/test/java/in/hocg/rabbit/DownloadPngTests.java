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
            "https://6d69-miniprogram-hat-4gxgmz47c042439e-1309189111.tcb.qcloud.la/puma-img/1.jpg",
            "https://6d69-miniprogram-hat-4gxgmz47c042439e-1309189111.tcb.qcloud.la/puma-img/2.jpg",
            "https://6d69-miniprogram-hat-4gxgmz47c042439e-1309189111.tcb.qcloud.la/puma-img/3.jpg",
            "https://6d69-miniprogram-hat-4gxgmz47c042439e-1309189111.tcb.qcloud.la/puma-img/4.jpg",
            "https://6d69-miniprogram-hat-4gxgmz47c042439e-1309189111.tcb.qcloud.la/puma-img/5.jpg",
            "https://6d69-miniprogram-hat-4gxgmz47c042439e-1309189111.tcb.qcloud.la/puma-img/6.jpg",
            "https://6d69-miniprogram-hat-4gxgmz47c042439e-1309189111.tcb.qcloud.la/puma-img/7.jpg",
            "https://6d69-miniprogram-hat-4gxgmz47c042439e-1309189111.tcb.qcloud.la/puma-img/8.jpg",
            "https://6d69-miniprogram-hat-4gxgmz47c042439e-1309189111.tcb.qcloud.la/puma-img/9.jpg",
            "https://6d69-miniprogram-hat-4gxgmz47c042439e-1309189111.tcb.qcloud.la/puma-img/10.jpg"
        );

        // frame
        for (String url : list) {
            String filename = FileUtil.getName(url);
            filename = StrUtil.format("sd_frame__{}_{}", System.currentTimeMillis(), filename);
            HttpUtil.downloadFile(url, Path.of(toPath, filename).toFile());
        }

    }

}
