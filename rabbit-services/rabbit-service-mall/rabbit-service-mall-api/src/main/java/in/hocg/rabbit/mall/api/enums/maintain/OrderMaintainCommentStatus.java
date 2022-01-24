package in.hocg.rabbit.mall.api.enums.maintain;

import in.hocg.boot.utils.annotation.UseDataDictKey;
import in.hocg.boot.utils.enums.DataDictEnum;
import in.hocg.rabbit.mall.api.named.MallDataDictKeys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by hocgin on 2022/1/15
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
@UseDataDictKey(value = MallDataDictKeys.ORDER_MAINTAIN_COMMENT_STATUS, description = "评论状态")
public enum OrderMaintainCommentStatus implements DataDictEnum {
    NotCommented("not_commented", "未评价"),
    Commented("commented", "已评价"),
    ;
    private final String code;
    private final String name;

}
