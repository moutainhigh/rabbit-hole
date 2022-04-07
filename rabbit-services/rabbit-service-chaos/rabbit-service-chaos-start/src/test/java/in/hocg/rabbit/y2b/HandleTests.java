package in.hocg.rabbit.y2b;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import in.hocg.boot.javacv.autoconfiguration.support.FeatureHelper;
import in.hocg.boot.test.autoconfiguration.core.AbstractSpringBootTest;
import in.hocg.boot.utils.LangUtils;
import in.hocg.rabbit.chaos.BootApplication;
import in.hocg.rabbit.common.utils.CommonUtils;
import in.hocg.rabbit.mina.biz.manager.VideoService;
import in.hocg.rabbit.mina.biz.pojo.dto.UploadY2bDto;
import in.hocg.rabbit.mina.biz.props.MinaProperties;
import in.hocg.rabbit.mina.biz.support.down.Video;
import in.hocg.rabbit.mina.biz.support.down.dto.Top;
import in.hocg.rabbit.mina.biz.support.down.dto.VideoInfo;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.io.File;
import java.nio.file.Path;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
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
        List<Top<VideoInfo>> result = Video.getVideoDecoder(Video.Type.DuoYin).topAweme();
        System.out.println(result);
    }

    @Test
    @ApiOperation("合集(全球诡异时代)上传")
    public void upload1() {
        String title = "《全球诡异时代》{ep}穿越者，在这个诡异的世界，正在追求着超凡的力量! #穿越 #异界";
        Long channelId = 1L;
        String url = "https://v.douyin.com/NqFKLcM/";
        List<String> addTags = List.of("穿越", "异界");
        File thumbFile = CommonUtils.toFile("http://cdn.hocgin.top/file/bf9e20b1ba43467ba20c8b1c4f3e0a4c.jpeg");
        uploadCollect(channelId, url, title, addTags, thumbFile);
    }

    @Test
    @ApiOperation("合集(全球诡异时代 1-25)上传")
    public void upload12() {
        String title = "《全球诡异时代》{ep}穿越者，在这个诡异的世界，正在追求着超凡的力量! #穿越 #异界";
        Long channelId = 1L;
        String url = "https://v.douyin.com/NqFKLcM/";
        List<String> addTags = List.of("穿越", "异界");
        File thumbFile = CommonUtils.toFile("http://cdn.hocgin.top/file/bf9e20b1ba43467ba20c8b1c4f3e0a4c.jpeg");

        int i = 3;
        uploadCollect(channelId, url, title, addTags, thumbFile, (25 * (i - 1)), (25 * i) - 1);
    }

    @Test
    @ApiOperation("合集(观棋烂柯 1-25)上传")
    public void upload13() {
        String title = "《观棋烂柯》{ep}烂柯旁棋局落叶，老树间对弈无人。传说中的故事居然是真的! #古风 #玄幻";
        Long channelId = 1L;
        String url = "https://v.douyin.com/NpqgRFx";
        List<String> addTags = List.of("古风", "玄幻");
        File thumbFile = CommonUtils.toFile("http://cdn.hocgin.top/file/4889082bdf1a4d78877d7b8a24590479.jpeg");

        int i = 3;
        uploadCollect(channelId, url, title, addTags, thumbFile, (25 * (i - 1)), (25 * i) - 1);
    }

    @Test
    @ApiOperation("合集(我的成就有点多 1-25)上传")
    public void upload14() {
        String title = "《我的成就有点多》{ep}为了奖励孟凡重生前义举，获得一个系统奖励，并将他送到三个月前! #都市 #异能 #系统";
        Long channelId = 1L;
        String url = "https://v.douyin.com/NsDEdpX/";
        List<String> addTags = List.of("都市", "异能", "系统");
        File thumbFile = CommonUtils.toFile("http://cdn.hocgin.top/file/4889082bdf1a4d78877d7b8a24590479.jpeg");

        int i = 1;
        uploadCollect(channelId, url, title, addTags, thumbFile, (25 * (i - 1)), (25 * i) - 1);
    }

    @Test
    @ApiOperation("单个(测试)上传")
    public void upload2() {
        String collectionName = "猫猫的日常(20220501)";
        String title = "猫猫的日常";
        String desc = title;
        Long channelId = 1L;
        List<String> urls = List.of(
            "https://v.douyin.com/NVENCyc",
            "https://v.douyin.com/NVENCyc"
        );
        List<String> addTags = List.of("猫咪");
        File thumbFile = CommonUtils.toFile("http://cdn.hocgin.top/file/bf9e20b1ba43467ba20c8b1c4f3e0a4c.jpeg");
        uploadDetail(collectionName, channelId, urls, title, desc, addTags, thumbFile);
    }

    @Test
    @ApiOperation("已有(测试)上传")
    public void uploadFile() {
        String title = "《全球诡异时代》{ep}穿越者，在这个诡异的世界，正在追求着超凡的力量! #穿越 #异界";
        String desc = title;
        Long channelId = 1L;
        List<String> addTags = List.of("穿越", "异界");
        File thumbFile = CommonUtils.toFile("http://cdn.hocgin.top/file/bf9e20b1ba43467ba20c8b1c4f3e0a4c.jpeg");
        File videoFile = new File("/Users/Share/k8s_nfs/basic_video/全球诡异时代(0~74)");
        upload(channelId, title, desc, addTags, videoFile, thumbFile);
    }

    //============================================================================================================================================================
    @ApiOperation("单个上传")
    private void uploadDetail(String collectionName, Long channelId, List<String> urls, String title, String desc, List<String> addTags, File thumbFile) {
        Path diskPath = Path.of(properties.getDiskPath());

        // 0. 获取下载地址
        List<VideoInfo> downloadUrls = videoService.getDownloadUrls(urls);

        // 1. 下载
        List<File> downloadFiles = videoService.download(downloadUrls, diskPath.resolve(collectionName).toFile());

        // 2. 合并
        String fname = StrUtil.format("{}({}~{}).mp4", collectionName, 1, downloadFiles.size());
        Path mergeFile = diskPath.resolve(fname);
        FeatureHelper.mergeVideo(downloadFiles, mergeFile.toFile());

        // 3. 调整文件
        File finalFile = videoService.modifyFile(mergeFile.toFile());

        // 4. 上传
        upload(channelId, title, desc, addTags, finalFile, thumbFile);
    }

    @ApiOperation("合集上传")
    private void uploadCollect(Long channelId, String url, String title, List<String> addTags, File thumbFile) {
        uploadCollect(channelId, url, title, addTags, thumbFile, null, null);
    }

    @ApiOperation("合集上传")
    private void uploadCollect(Long channelId, String url, String title, List<String> addTags, File thumbFile,
                               Integer startIndex, Integer endIndex) {
        Path diskPath = Path.of(properties.getDiskPath());

        List<VideoInfo> videos = Video.getVideoDecoder(Video.Type.DuoYin).listAweme(url);
        VideoInfo videoInfo = videos.get(0);
        String collectionName = videoInfo.getTitle();

        // 2. 下载视频
        List<File> files = videoService.download(videos,
            diskPath.resolve(collectionName).toFile());

        // 3. 合并
        startIndex = LangUtils.getOrDefault(startIndex, 0);
        endIndex = LangUtils.getOrDefault(endIndex, files.size());
        List<File> mergeFiles = CollUtil.sub(files, startIndex, endIndex);

        String fname = StrUtil.format("{}({}~{}).mp4", collectionName, startIndex + 1, endIndex + 1);
        Path mergeFile = diskPath.resolve(fname);
        FeatureHelper.mergeVideo(mergeFiles, mergeFile.toFile(), 0, 3 * (1000 * 1000));

        // 4. 调整文件
        videoService.modifyFile(mergeFile.toFile());

        // 4. 上传
        UploadY2bDto options = new UploadY2bDto();
        options.setTitle(title(title, startIndex + 1, endIndex + 1));
        options.setThumbFile(thumbFile);
        List<String> tags = Lists.newArrayList(videoInfo.getKeywords());
        tags.addAll(addTags);
        options.setTags(tags.stream().filter(Objects::nonNull).collect(Collectors.toList()));
        options.setDescription(videoInfo.getDesc());
        upload(channelId, mergeFile.toFile(), options);
    }

    private String title(String tpl, Integer start, Integer end) {
        Map<String, String> params = Maps.newHashMap();
        params.put("ep", (Objects.nonNull(start) && Objects.nonNull(end)) ? StrUtil.format("[EP{}-{}] ", start, end) : "");
        return StrUtil.format(tpl, params);
    }

    private void upload(Long channelId, File videoFile, UploadY2bDto options) {
        upload(channelId, options.getTitle(), options.getDescription(), options.getTags(), videoFile, options.getThumbFile());
    }

    private void upload(Long channelId, String title, String desc, List<String> tags, File videoFile,
                        File thumbFile) {
        UploadY2bDto options = new UploadY2bDto();
        options.setTitle(title);
        options.setThumbFile(thumbFile);
        options.setTags(tags.stream().filter(Objects::nonNull)
            .filter(s -> !StrUtil.contains(s, "抖音"))
            .collect(Collectors.toList()));
        options.setDescription(desc);
        videoService.upload(channelId, videoFile, options);
    }
}
