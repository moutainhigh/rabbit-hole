package in.hocg.rabbit.com.biz.pojo.dto;

import lombok.Data;

/**
 * Created by hocgin on 2021/9/5
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class CreateCommentUserActionDto {
    private Long commentId;
    private Long userId;
    private String action;
}
