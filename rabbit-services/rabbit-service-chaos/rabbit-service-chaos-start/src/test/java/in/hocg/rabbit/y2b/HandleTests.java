package in.hocg.rabbit.y2b;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.google.api.client.util.Lists;
import in.hocg.boot.javacv.autoconfiguration.support.FeatureHelper;
import in.hocg.rabbit.chaos.dvideo.Video;
import in.hocg.rabbit.chaos.dvideo.dto.VideoInfo;
import in.hocg.rabbit.common.utils.CommonUtils;
import io.swagger.annotations.ApiModelProperty;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.junit.Test;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by hocgin on 2022/4/3
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
public class HandleTests {
    String disk = "/Users/hocgin/Downloads/";


    public static void main(String[] args) {
        // 抖音排行榜 https://www.cnblogs.com/linn/p/12124330.html

        // https://www.iesdouyin.com/web/api/mix/item/list/?mix_id=7043364295899514887&count=10&cursor=0
        String url = "https://v.douyin.com/NqFKLcM/";
        List<VideoInfo> list = Video.getVideoDecoder(Video.Type.DuoYin).list(url);
        System.out.println(list);
    }

    @Test
    public void test() {

        String title = "测试.mp4";
        List<String> urls = List.of(
            "https://v.douyin.com/NVENCyc",
            "https://v.douyin.com/NVENCyc"
        );

        // 1. 下载
        File disk = Paths.get(this.disk, "download").toFile();
        List<File> downloadFiles = download(urls, disk);

        // 2. 合并
        File mergeFile = Paths.get(this.disk, "merge", title).toFile();
        merge(downloadFiles, mergeFile);

        // 3. 调整文件
        File finalFile = modifyFile(mergeFile);

        // 4. 上传
        File uploadFile = upload(finalFile, new UploadVideoInfoDto());

        // 5. 存档
        File toCopyFile = Paths.get(this.disk, "archive", title).toFile();
        archive(uploadFile, toCopyFile);
    }

    @ApiModelProperty("1. 下载连接")
    private List<File> download(List<String> urls, File disk) {
        long prefix = System.currentTimeMillis();

        FileUtil.mkdir(disk);
        List<File> result = Lists.newArrayList();
        for (int i = 0; i < urls.size(); i++) {
            String url = urls.get(i);
            String fileName = StrUtil.format("{}_{}_{}.mp4", prefix, i, RandomUtil.randomString(5));
            Path toFilePath = Paths.get(disk.getAbsolutePath(), fileName);
            String nUrl = Video.decode(url, Video.Type.DuoYin);
            result.add(CommonUtils.downloadFile(nUrl, toFilePath));
        }
        return result;
    }

    @ApiModelProperty("2. 合并视频")
    private File merge(List<File> files, File toFile) {
        Assert.notEmpty(files, "files is empty");
        FileUtil.mkParentDirs(toFile);
        if (files.size() == 1) {
            return FileUtil.copyFile(files.get(0), toFile);
        }
        return FeatureHelper.mergeVideo(files, toFile);
    }

    @ApiModelProperty("3. 调整文件")
    private File modifyFile(File file) {
        log.info("调整前 md5=[{}]", SecureUtil.md5(file));
        File finalFile = CommonUtils.updateFileMd5(file);
        log.info("调整后 md5=[{}]", SecureUtil.md5(file));
        return finalFile;
    }

    @ApiModelProperty("4. 上传文件")
    private File upload(File file, UploadVideoInfoDto dto) {

        return file;
    }

    @ApiModelProperty("5. 存档")
    private File archive(File file, File toCopyFile) {
        FileUtil.mkParentDirs(toCopyFile);
        FileUtil.copyFile(file, toCopyFile);
        return toCopyFile;
    }
}
