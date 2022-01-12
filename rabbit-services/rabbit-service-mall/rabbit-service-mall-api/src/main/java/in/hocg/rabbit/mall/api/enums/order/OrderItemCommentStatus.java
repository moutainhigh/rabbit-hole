package in.hocg.rabbit.mall.api.enums.order;

import in.hocg.boot.utils.annotation.UseDataDictKey;
import in.hocg.boot.utils.enums.DataDictEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by hocgin on 2022/1/14
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
@UseDataDictKey(description = "评价状态")
public enum OrderItemCommentStatus implements DataDictEnum {
    NotCommented("not_commented", "未评价"),
    Commented("commented", "已评价"),
    ;
    private final Serializable code;
    private final String name;
}
