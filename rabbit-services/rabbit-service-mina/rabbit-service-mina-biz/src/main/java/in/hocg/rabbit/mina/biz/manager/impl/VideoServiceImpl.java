package in.hocg.rabbit.mina.biz.manager.impl;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Assert;
import cn.hutool.core.util.RandomUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import com.google.common.collect.Lists;
import in.hocg.boot.utils.LangUtils;
import in.hocg.rabbit.common.utils.CommonUtils;
import in.hocg.rabbit.cv.api.CvServiceApi;
import in.hocg.rabbit.mina.biz.manager.VideoService;
import in.hocg.rabbit.mina.biz.manager.YouTubeService;
import in.hocg.rabbit.mina.biz.pojo.dto.UploadY2bDto;
import in.hocg.rabbit.mina.biz.pojo.ro.UploadYouTubeVideoRo;
import in.hocg.rabbit.mina.biz.support.down.Video;
import in.hocg.rabbit.mina.biz.support.down.dto.VideoInfo;
import io.swagger.annotations.ApiModelProperty;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by hocgin on 2022/4/3
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class VideoServiceImpl implements VideoService {
    private final CvServiceApi cvServiceApi;
    private final YouTubeService youTubeService;

    @Override
    @ApiModelProperty("0. 获取资源地址")
    public List<VideoInfo> getVideoWithCollection(String url) {
        return Video.getVideoDecoder(Video.Type.DuoYin).list(url);
    }

    @Override
    @ApiModelProperty("0. 获取资源地址")
    public List<String> getDownloadUrls(List<String> urls) {
        return urls.stream().map(url -> Video.decode(url, Video.Type.DuoYin)).collect(Collectors.toList());
    }

    @Override
    @ApiModelProperty("1. 下载连接")
    public List<File> download(List<String> urls, File disk) {
        FileUtil.mkdir(disk);
        List<File> result = Lists.newArrayList();
        for (String url : urls) {
            String fileName = StrUtil.format("{}.mp4", SecureUtil.md5(url));
            Path toFilePath = Paths.get(disk.getAbsolutePath(), fileName);
            result.add(CommonUtils.downloadFile(url, toFilePath));
        }
        return result;
    }

    @Override
    @ApiModelProperty("2. 合并视频")
    public File merge(List<File> files, File toFile) {
        Assert.notEmpty(files, "files is empty");
        FileUtil.mkParentDirs(toFile);
        if (files.size() == 1) {
            return FileUtil.copyFile(files.get(0), toFile);
        }
        cvServiceApi.mergeVideo(LangUtils.toList(files, File::getAbsolutePath), toFile.getAbsolutePath());
        return toFile;
    }

    @Override
    @ApiModelProperty("3. 调整文件")
    public File modifyFile(File file) {
        log.info("调整前 md5=[{}]", SecureUtil.md5(file));
        File finalFile = CommonUtils.updateFileMd5(file);
        log.info("调整后 md5=[{}]", SecureUtil.md5(file));
        return finalFile;
    }

    @Override
    @ApiModelProperty("4. 上传文件")
    public File upload(Long channelId, File videoFile, UploadY2bDto dto) {
        youTubeService.uploadVideo(channelId, videoFile, dto);
        return videoFile;
    }

    @Override
    @ApiModelProperty("5. 存档")
    public File archive(File file, File toCopyFile) {
        FileUtil.mkParentDirs(toCopyFile);
        FileUtil.copyFile(file, toCopyFile);
        return toCopyFile;
    }
}
