package in.hocg.rabbit.y2b;

import cn.hutool.core.util.StrUtil;
import in.hocg.rabbit.mina.biz.manager.impl.VideoServiceImpl;
import in.hocg.rabbit.mina.biz.pojo.dto.UploadY2bDto;
import in.hocg.rabbit.mina.biz.support.down.Video;
import in.hocg.rabbit.mina.biz.support.down.dto.VideoInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.io.File;
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
    public void collectUrl() {
        String url = "https://v.douyin.com/NqFKLcM/";
        List<VideoInfo> list = Video.getVideoDecoder(Video.Type.DuoYin).list(url);
        System.out.println(list);

        // 播放位置
        System.out.println(StrUtil.format("{}~{}", 1, list.size()));
    }

    @Test
    public void test() {
        String title = "测试.mp4";
        List<String> urls = List.of(
            "https://v.douyin.com/NVENCyc",
            "https://v.douyin.com/NVENCyc"
        );
        VideoServiceImpl videoService = null;

        // 0. 获取下载地址
        List<String> downloadUrls = videoService.getDownloadUrls(urls);

        // 1. 下载
        File disk = Paths.get(this.disk, "download").toFile();
        List<File> downloadFiles = videoService.download(downloadUrls, disk);

        // 2. 合并
        File mergeFile = Paths.get(this.disk, "merge", title).toFile();
        videoService.merge(downloadFiles, mergeFile);

        // 3. 调整文件
        File finalFile = videoService.modifyFile(mergeFile);

        // 4. 上传
        File uploadFile = finalFile;

        // 5. 存档
        File toCopyFile = Paths.get(this.disk, "archive", title).toFile();
        videoService.archive(uploadFile, toCopyFile);
    }
}
