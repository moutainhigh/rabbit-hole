package in.hocg.rabbit.com.api.enums.comment;

import in.hocg.boot.utils.annotation.UseDataDictKey;
import in.hocg.boot.utils.enums.DataDictEnum;
import in.hocg.rabbit.com.api.named.ComDataDictKeys;
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
@UseDataDictKey(value = ComDataDictKeys.COMMENT_USER_ACTION_TYPE, description = "评论用户行为类型")
public enum CommentUserActionType implements DataDictEnum {
    None("none", "未操作"),
    Like("like", "点赞"),
    Dislike("dislike", "倒赞"),
    ;
    private final String code;
    private final String name;
}
