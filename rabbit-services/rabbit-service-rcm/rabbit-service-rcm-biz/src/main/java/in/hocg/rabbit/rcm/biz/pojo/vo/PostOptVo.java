package in.hocg.rabbit.rcm.biz.pojo.vo;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * Created by hocgin on 2022/4/29
 * email: hocgin@gmail.com
 * A 回复了 B 的文章
 * A 回复了 B 的评论
 *
 * @author hocgin
 */
@Data
@ApiModel
public class PostOptVo {
    private Long triggerUserId;
    private String optType;

    @Data
    public class triggerPost {
        private Long id;
        private String title;
    }

    @Data
    public class ReplyCommit {
        private Long id;
        private String userId;
    }

    @Data
    public class ReplyPost {
        private Long id;
        private String userId;
    }


}
