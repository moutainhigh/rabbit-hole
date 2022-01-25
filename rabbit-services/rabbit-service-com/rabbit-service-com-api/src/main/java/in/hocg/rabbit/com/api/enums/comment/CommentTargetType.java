package in.hocg.rabbit.com.api.enums.comment;

import in.hocg.boot.utils.annotation.UseDataDictKey;
import in.hocg.boot.utils.enums.DataDictEnum;
import in.hocg.rabbit.com.api.named.ComDataDictKeys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by hocgin on 2021/1/13
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
@UseDataDictKey(value = ComDataDictKeys.COMMENT_TARGET_TYPE, description = "评论目标类型")
public enum CommentTargetType implements DataDictEnum {
    Article("article", "文章"),
    Comment("comment", "评论")
    ;
    private final Serializable code;
    private final String name;
}
