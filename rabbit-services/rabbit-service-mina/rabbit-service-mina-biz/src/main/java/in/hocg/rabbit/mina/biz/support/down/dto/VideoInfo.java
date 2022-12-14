package in.hocg.rabbit.mina.biz.support.down.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Created by hocgin on 2022/4/3
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class VideoInfo {
    /**
     * 唯一标记
     */
    private String id;
    /**
     * 标题
     */
    private String title;
    /**
     * 视频地址
     */
    private String url;
    /**
     * 视频地址(有水印)
     */
    private String originalUrl;
    /**
     * 关键词
     */
    private List<String> keywords;
    /**
     * 描述
     */
    private String desc;
    /**
     * 时长(毫秒)
     */
    private Long duration;
}
