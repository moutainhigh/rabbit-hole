package in.hocg.rabbit.com.api.enums;

import in.hocg.boot.utils.annotation.UseDataDictKey;
import in.hocg.boot.utils.enums.DataDictEnum;
import in.hocg.rabbit.com.api.named.ComDataDictKeys;
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
@UseDataDictKey(value = ComDataDictKeys.TASK_TYPE, description = "任务类型")
public enum TaskType implements DataDictEnum {
    Unknown("unknown", "未知"),
    YouTubeUpload("youtube_upload", "YOUTUBE 视频上传");
    private final Serializable code;
    private final String name;
}
