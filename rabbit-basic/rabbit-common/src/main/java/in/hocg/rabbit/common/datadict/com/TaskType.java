package in.hocg.rabbit.common.datadict.com;

import in.hocg.boot.utils.enums.DataDictEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by hocgin on 2021/6/19
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
public enum TaskType implements DataDictEnum {
    Unknown("unknown", "未知"),
    YouTubeUpload("youtube_upload", "YOUTUBE 视频上传");
    private final Serializable code;
    private final String name;
}