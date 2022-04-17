package in.hocg.rabbit.com.api.pojo.vo;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by hocgin on 2022/4/17
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class LastCommentVo implements Serializable {
    private Long creator;
    private LocalDateTime createdAt;
}
