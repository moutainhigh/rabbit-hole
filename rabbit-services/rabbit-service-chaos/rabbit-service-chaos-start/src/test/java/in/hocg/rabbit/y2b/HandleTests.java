package in.hocg.rabbit.y2b;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
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
import org.apache.commons.lang3.tuple.Pair;
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
    @ApiOperation("合集(全球诡异时代|2.5min|周二)上传")
    public void upload12() {
        String title = "《全球诡异时代》{ep}穿越者，在这个诡异的世界，正在追求着超凡的力量! #穿越 #异界";
        Long channelId = 1L;
        String url = "https://v.douyin.com/NqFKLcM/";
        List<String> addTags = List.of("穿越", "异界");
        File thumbFile = CommonUtils.toFile("http://cdn.hocgin.top/file/bf9e20b1ba43467ba20c8b1c4f3e0a4c.jpeg");

        Pair<Integer, Integer> pair = buildPage(1, 15, 75);
        uploadCollect(channelId, url, title, addTags, thumbFile, pair.getLeft(), pair.getRight(), null);
    }

    @Test
    @ApiOperation("合集(观棋烂柯|3min|周三)上传")
    public void upload13() {
        // https://www.gaoding.com/design?id=19564228136026199&simple=1&mode=user
        String title = "《观棋烂柯》{ep}烂柯旁棋局落叶，老树间对弈无人。传说中的故事居然是真的! #古风 #玄幻";
        Long channelId = 1L;
        String url = "https://v.douyin.com/NpqgRFx";
        List<String> addTags = List.of("古风", "玄幻");
        File thumbFile = CommonUtils.toFile("http://cdn.hocgin.top/file/4889082bdf1a4d78877d7b8a24590479.jpeg");

        // -> 1, 14, 66 => 80
        // -> 1, 10, 80
        Pair<Integer, Integer> pair = buildPage(1, 14, 66);
        uploadCollect(channelId, url, title, addTags, thumbFile, pair.getLeft(), pair.getRight(), null);
    }

    @Test
    @ApiOperation("合集(我的成就有点多|2min|周四)上传")
    public void upload14() {
        String title = "《我的成就有点多》{ep}为了奖励孟凡重生前义举，获得一个系统奖励，并将他送到三个月前! #都市 #异能 #系统";
        Long channelId = 1L;
        String url = "https://v.douyin.com/NsDEdpX/";
        List<String> addTags = List.of("都市", "异能", "系统");
        File thumbFile = CommonUtils.toFile("http://cdn.hocgin.top/file/4889082bdf1a4d78877d7b8a24590479.jpeg");

        Pair<Integer, Integer> pair = buildPage(1, 15, 50);
        uploadCollect(channelId, url, title, addTags, thumbFile, pair.getLeft(), pair.getRight(), null);
    }

    @Test
    @ApiOperation("合集(我有999种异能|2.5min|周五)上传")
    public void upload15() {
        // https://www.gaoding.com/design?mode=user&id=19575703892148247
        String title = "《我有999种异能》{ep}在这个全民异能的世界，为了拯救心爱的妹妹，杨希发誓要让伤害她的人血债血偿! #都市 #异能 #系统";
        Long channelId = 1L;
        String url = "https://v.douyin.com/NGkcuvb/";
        List<String> addTags = List.of("都市", "异能", "系统");
        File thumbFile = CommonUtils.toFile("http://cdn.hocgin.top/file/4889082bdf1a4d78877d7b8a24590479.jpeg");

        Pair<Integer, Integer> pair = buildPage(2, 15, 0);
        uploadCollect(channelId, url, title, addTags, thumbFile, pair.getLeft(), pair.getRight(), null);
    }

    @Test
    @ApiOperation("合集(末日重启|1.5min|周五)上传")
    public void upload16() {
        // https://www.gaoding.com/design?mode=user&id=19581889897185313
        String title = "《末日重启》{ep}末日爆发后奋战了三年，因为队友连累被丧尸围攻致死。再次醒来，却重生末日开始之前! #末日 #重生";
        Long channelId = 1L;
        String url = "https://v.douyin.com/NtenCEv";
        List<String> addTags = List.of("末日", "重生");
        File thumbFile = CommonUtils.toFile("http://cdn.hocgin.top/file/4889082bdf1a4d78877d7b8a24590479.jpeg");

        Pair<Integer, Integer> pair = buildPage(2, 25, 0);
        uploadCollect(channelId, url, title, addTags, thumbFile, pair.getLeft(), pair.getRight(), null);
    }

    @Test
    @ApiOperation("合集(深渊副本已刷新|1.5min|周六.上午)上传")
    public void upload17() {
        // https://www.gaoding.com/design?id=19587528967596099&simple=1&mode=user
        String title = "《深渊副本已刷新》{ep}十年前神秘建筑出现在城市上空，被选中的玩家能够进入天空城中，不断地积累声望和财富! #副本 #都市";
        Long channelId = 1L;
        String url = "https://v.douyin.com/NtPhKsK";
        List<String> addTags = List.of("游戏", "副本", "升级");
        File thumbFile = CommonUtils.toFile("http://cdn.hocgin.top/file/4889082bdf1a4d78877d7b8a24590479.jpeg");

        Pair<Integer, Integer> pair = buildPage(2, 20, 0);
        uploadCollect(channelId, url, title, addTags, thumbFile, pair.getLeft(), pair.getRight(), null);
    }


    @Test
    @ApiOperation("合集(九个女徒弟称霸后宫|2min|周六.下午)上传")
    public void upload18() {
        // https://www.gaoding.com/design?mode=user&id=19588056655865891
        String title = "《九个女徒弟称霸后宫》{ep}敢在老祖头上动土？打脸打不死你！女人？我有九个！！ #后宫 #重生";
        Long channelId = 1L;
        String url = "https://v.douyin.com/NtmohKM/";
        List<String> addTags = List.of("热血", "后宫", "重生");
        File thumbFile = CommonUtils.toFile("http://cdn.hocgin.top/file/4889082bdf1a4d78877d7b8a24590479.jpeg");

        Pair<Integer, Integer> pair = buildPage(2, 20, 0);
        uploadCollect(channelId, url, title, addTags, thumbFile, pair.getLeft(), pair.getRight(),
            "PLCEcFGOrM-f8iRJ_DRQlwhKjKDF6h9uJE");
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


        UploadY2bDto options = new UploadY2bDto();
        options.setTitle(title);
        options.setThumbFile(thumbFile);
        options.setTags(addTags);
        options.setDescription(desc);
        upload(channelId, videoFile, options);
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
        UploadY2bDto options = new UploadY2bDto();
        options.setTitle(title);
        options.setThumbFile(thumbFile);
        options.setTags(addTags);
        options.setDescription(desc);
        upload(channelId, finalFile, options);
    }

    @ApiOperation("合集上传")
    private void uploadCollect(Long channelId, String url, String title, List<String> addTags, File thumbFile) {
        uploadCollect(channelId, url, title, addTags, thumbFile, null, null, null);
    }

    /**
     * @param channelId
     * @param url
     * @param title
     * @param addTags
     * @param thumbFile
     * @param epStart   开始(包含)
     * @param epEnd     结束(包含)
     */
    @ApiOperation("合集上传")
    private void uploadCollect(Long channelId, String url, String title, List<String> addTags, File thumbFile, Integer epStart, Integer epEnd, String playlistId) {


        Path diskPath = Path.of(properties.getDiskPath());

        List<VideoInfo> videos = Video.getVideoDecoder(Video.Type.DuoYin).listAweme(url);
        VideoInfo videoInfo = videos.get(0);
        String collectionName = SecureUtil.md5(url);

        // 2. 下载视频
        List<File> files = videoService.download(videos, diskPath.resolve(collectionName).toFile());

        // 下标范围
        int epMax = files.size();
        epStart = LangUtils.getOrDefault(epStart, 1);
        epEnd = Math.min(LangUtils.getOrDefault(epEnd, epMax), epMax);
        Assert.isTrue(epStart < epEnd, "错误的下标: [{}, {}]", epStart, epEnd);

        // 2.2 获取待合并视频
        List<File> mergeFiles = CollUtil.sub(files, epStart - 1, epEnd);

        // 3. 合并
        String fname = StrUtil.format("{}({}~{}).mp4", collectionName, epStart, epEnd);
        Path mergeFile = diskPath.resolve(fname);
        FeatureHelper.mergeVideo(mergeFiles, mergeFile.toFile(), 0, Convert.toLong(2.8 * (1000 * 1000)));

        // 4. 调整文件
        videoService.modifyFile(mergeFile.toFile());

        // 4. 上传
        UploadY2bDto options = new UploadY2bDto();
        options.setTitle(title(title, epStart, epEnd));
        options.setThumbFile(thumbFile);
        List<String> tags = Lists.newArrayList(videoInfo.getKeywords());
        tags.addAll(addTags);
        options.setTags(tags.stream().filter(Objects::nonNull).collect(Collectors.toList()));
        options.setDescription(videoInfo.getDesc());
        options.setPlaylistId(playlistId);
        upload(channelId, mergeFile.toFile(), options);
    }

    /**
     * 得出开始的下标和结束的下标
     * [1, 25]
     * [26, 50]
     *
     * @param page
     * @param pageSize
     * @param baseIdx
     * @return [epStart, epEnd]
     */
    private Pair<Integer, Integer> buildPage(int page, int pageSize, int baseIdx) {
        int startIdx = baseIdx + (pageSize * (Math.max(page, 1) - 1));
        int endIdx = startIdx + pageSize;
        return Pair.of(startIdx + 1, endIdx);
    }

    private String title(String tpl, Integer start, Integer end) {
        Map<String, String> params = Maps.newHashMap();
        params.put("ep", (Objects.nonNull(start) && Objects.nonNull(end)) ? StrUtil.format("[EP{}-{}] ", start, end) : "");
        return StrUtil.format(tpl, params);
    }

    private void upload(Long channelId, File videoFile, UploadY2bDto options) {
        List<String> tags = options.getTags();
        String desc = options.getDescription();
        File thumbFile = options.getThumbFile();
        String title = options.getTitle();

        List<String> limitTags = tags.stream()
            .filter(s -> StrUtil.contains(s, "抖音"))
            .filter(s -> StrUtil.contains(s, "计划"))
            .collect(Collectors.toList());

        // 过滤掉描述里面的标签
        String filterDesc = desc;
        for (String limitTag : limitTags) {
            filterDesc = StrUtil.removeAny(filterDesc, "#" + limitTag);
        }
        filterDesc = filterDesc.trim();

        // 过滤掉标签
        List<String> filteredTags = CollUtil.removeAny(tags, limitTags.toArray(new String[0]));

        UploadY2bDto newOptions = new UploadY2bDto();
        newOptions.setTitle(title);
        newOptions.setThumbFile(thumbFile);
        newOptions.setTags(filteredTags);
        newOptions.setDescription(filterDesc);
        newOptions.setPlaylistId(options.getPlaylistId());
        videoService.upload(channelId, videoFile, newOptions);
    }
}
