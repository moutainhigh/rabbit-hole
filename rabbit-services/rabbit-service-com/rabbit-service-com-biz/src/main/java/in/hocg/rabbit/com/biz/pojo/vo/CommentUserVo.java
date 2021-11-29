package in.hocg.rabbit.com.biz.pojo.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2021/1/13
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
@Accessors(chain = true)
public class CommentUserVo {
    private Long id;
    private String nickname;
    private String avatarUrl;
    private String href;
}
