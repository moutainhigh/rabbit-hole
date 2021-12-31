package in.hocg.rabbit.com.api.enums;

import in.hocg.boot.utils.enums.DataDictEnum;
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
public enum CommentUserActionType implements DataDictEnum {
    None("none", "未操作"),
    Like("like", "点赞"),
    Dislike("dislike", "倒赞"),
    ;
    private final String code;
    private final String name;
}
