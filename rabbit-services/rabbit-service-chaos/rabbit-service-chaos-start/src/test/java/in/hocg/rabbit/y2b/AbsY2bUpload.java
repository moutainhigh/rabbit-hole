package in.hocg.rabbit.y2b;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.convert.Convert;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import in.hocg.boot.javacv.autoconfiguration.support.FeatureHelper;
import in.hocg.boot.test.autoconfiguration.core.AbstractSpringBootTest;
import in.hocg.boot.utils.LangUtils;
import in.hocg.rabbit.mina.biz.manager.VideoService;
import in.hocg.rabbit.mina.biz.pojo.dto.UploadY2bDto;
import in.hocg.rabbit.mina.biz.props.MinaProperties;
import in.hocg.rabbit.mina.biz.support.down.Video;
import in.hocg.rabbit.mina.biz.support.down.dto.VideoInfo;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang3.tuple.Pair;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;
import java.nio.file.Path;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * Created by hocgin on 2022/4/10
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public abstract class AbsY2bUpload extends AbstractSpringBootTest {
    @Autowired
    private MinaProperties properties;
    @Autowired
    private VideoService videoService;

    //============================================================================================================================================================
    @ApiOperation("单个上传")
    void uploadDetail(String collectionName, Long channelId, List<String> urls, String title, String desc, List<String> addTags, File thumbFile) {
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
    void uploadCollect(Long channelId, String url, String title, List<String> addTags, File thumbFile) {
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
    void uploadCollect(Long channelId, String url, String title, List<String> addTags, File thumbFile, Integer epStart, Integer epEnd, String playlistId) {
        Path diskPath = Path.of(properties.getDiskPath());

        String collectionName = SecureUtil.md5(url);
        String desc = title;
        List<File> files;
        List<String> keywords = Collections.emptyList();
        if (FileUtil.isDirectory(url)) {
            files = FileUtil.loopFiles(url);
        } else {
            List<VideoInfo> videos = Video.getVideoDecoder(Video.Type.DuoYin).listAweme(url);
            VideoInfo videoInfo = videos.get(0);
            keywords = videoInfo.getKeywords();
            desc = videoInfo.getDesc();

            // 2. 下载视频
            files = videoService.download(videos, diskPath.resolve(collectionName).toFile());
        }

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
        List<String> tags = Lists.newArrayList(keywords);
        tags.addAll(addTags);
        options.setTags(tags.stream().filter(Objects::nonNull).collect(Collectors.toList()));
        options.setDescription(desc);
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
    Pair<Integer, Integer> buildPage(int page, int pageSize, int baseIdx) {
        int startIdx = baseIdx + (pageSize * (Math.max(page, 1) - 1));
        int endIdx = startIdx + pageSize;
        return Pair.of(startIdx + 1, endIdx);
    }

    String title(String tpl, Integer start, Integer end) {
        Map<String, String> params = Maps.newHashMap();
        params.put("ep", (Objects.nonNull(start) && Objects.nonNull(end)) ? StrUtil.format("[EP{}-{}] ", start, end) : "");
        return StrUtil.format(tpl, params);
    }

    void upload(Long channelId, File videoFile, UploadY2bDto options) {
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
