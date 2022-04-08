package in.hocg.rabbit.mina.biz.pojo.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2022/3/31
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class YouTubeClientVo {
    private String id;
    private String applicationName;
}
