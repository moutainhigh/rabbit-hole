package in.hocg.rabbit.mina.biz.support.down.dto;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2022/4/4
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class MusicInfo {
    private String id;
    private String title;
    private String author;
    private String playUrl;
    private String coverUrl;
    /**
     * 单位：秒
     */
    private Long duration;
}
