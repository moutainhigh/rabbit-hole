package in.hocg.rabbit.y2b;

import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import in.hocg.boot.test.autoconfiguration.core.AbstractSpringBootTest;
import in.hocg.boot.utils.LangUtils;
import in.hocg.rabbit.chaos.BootApplication;
import in.hocg.rabbit.mina.biz.manager.VideoService;
import in.hocg.rabbit.mina.biz.manager.impl.VideoServiceImpl;
import in.hocg.rabbit.mina.biz.pojo.dto.UploadY2bDto;
import in.hocg.rabbit.mina.biz.props.MinaProperties;
import in.hocg.rabbit.mina.biz.support.down.Video;
import in.hocg.rabbit.mina.biz.support.down.dto.VideoInfo;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by hocgin on 2022/4/3
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@ActiveProfiles("local")
@SpringBootTest(classes = {BootApplication.class}, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class HandleTests extends AbstractSpringBootTest {
    @Autowired
    private VideoService videoService;
    @Autowired
    private MinaProperties properties;


    public static void main(String[] args) {
        // 抖音排行榜 https://www.cnblogs.com/linn/p/12124330.html

        // https://www.iesdouyin.com/web/api/mix/item/list/?mix_id=7043364295899514887&count=10&cursor=0
        String url = "https://v.douyin.com/NqFKLcM/";
        List<VideoInfo> list = Video.getVideoDecoder(Video.Type.DuoYin).list(url);
        System.out.println(list);
    }

    @Test
    public void testCollectUrl() {
        Long channelId = 1L;
        String url = "https://v.douyin.com/NqFKLcM/";
        List<String> addTags = List.of();
        File thumbFile = null;


        // ===========================
        Path diskPath = Path.of(properties.getDiskPath());

        List<VideoInfo> videos = Video.getVideoDecoder(Video.Type.DuoYin).list(url);
        VideoInfo videoInfo = videos.get(0);
        String collectionName = videoInfo.getTitle();

        // 2. 下载视频
        List<File> files = videoService.download(videos,
            diskPath.resolve(collectionName).toFile());

        // 3. 合并
        String title = StrUtil.format("{}({}~{})", collectionName, 0, files.size());
        Path mergeFile = diskPath.resolve(title);
        videoService.merge(files, mergeFile.toFile());

        // 4. 调整文件
        videoService.modifyFile(mergeFile.toFile());

        // 4. 上传
        UploadY2bDto options = new UploadY2bDto();
        options.setTitle(title);
        options.setThumbFile(thumbFile);
        List<String> tags = Lists.newArrayList(videoInfo.getKeywords());
        tags.addAll(addTags);
        options.setTags(tags.stream().filter(Objects::nonNull).collect(Collectors.toList()));
        options.setDescription(videoInfo.getDesc());
        upload(channelId, mergeFile.toFile(), options);
    }

    @Test
    public void testDetail() {
        Long channelId = 1L;
        String collectionName = "测试";
        String desc = "";
        List<String> urls = List.of(
            "https://v.douyin.com/NVENCyc",
            "https://v.douyin.com/NVENCyc"
        );
        List<String> addTags = List.of();
        File thumbFile = null;

        // ===========================
        Path diskPath = Path.of(properties.getDiskPath());

        // 0. 获取下载地址
        List<VideoInfo> downloadUrls = videoService.getDownloadUrls(urls);

        // 1. 下载
        List<File> downloadFiles = videoService.download(downloadUrls, diskPath.resolve(collectionName).toFile());

        // 2. 合并
        String title = StrUtil.format("{}[{}.{}]", collectionName, 0, downloadFiles.size());
        Path mergeFile = diskPath.resolve(title);
        videoService.merge(downloadFiles, mergeFile.toFile());

        // 3. 调整文件
        File finalFile = videoService.modifyFile(mergeFile.toFile());

        // 4. 上传
        upload(channelId, title, desc, addTags, finalFile, thumbFile);
    }

    private void upload(Long channelId, File videoFile, UploadY2bDto options) {
        upload(channelId, options.getTitle(), options.getDescription(), options.getTags(), videoFile, options.getThumbFile());
    }

    private void upload(Long channelId, String title, String desc, List<String> tags, File videoFile,
                        File thumbFile) {
        UploadY2bDto options = new UploadY2bDto();
        options.setTitle(title);
        options.setThumbFile(thumbFile);
        options.setTags(tags.stream().filter(Objects::nonNull).collect(Collectors.toList()));
        options.setDescription(desc);
        videoService.upload(channelId, videoFile, options);
    }
}
