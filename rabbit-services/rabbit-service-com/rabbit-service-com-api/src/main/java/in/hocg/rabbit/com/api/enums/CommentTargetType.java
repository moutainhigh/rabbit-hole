package in.hocg.rabbit.com.api.enums;

import in.hocg.boot.utils.enums.DataDictEnum;
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
public enum CommentTargetType implements DataDictEnum {
    Article("article", "文章"),
    Comment("comment", "评论")
    ;
    private final Serializable code;
    private final String name;
}
