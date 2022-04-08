package in.hocg.rabbit.mina.biz.manager;

import in.hocg.rabbit.mina.biz.pojo.dto.UploadY2bDto;
import in.hocg.rabbit.mina.biz.support.down.dto.VideoInfo;
import io.swagger.annotations.ApiModelProperty;

import java.io.File;
import java.util.List;

/**
 * Created by hocgin on 2022/4/3
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface VideoService {
    List<VideoInfo> getVideoWithCollection(String url);

    List<VideoInfo> getDownloadUrls(List<String> urls);

    List<File> download(List<VideoInfo> videos, File disk);

    File merge(List<File> files, File toFile);

    File modifyFile(File file);

    File upload(Long channelId, File file, UploadY2bDto dto);

    File archive(File file, File toCopyFile);
}
