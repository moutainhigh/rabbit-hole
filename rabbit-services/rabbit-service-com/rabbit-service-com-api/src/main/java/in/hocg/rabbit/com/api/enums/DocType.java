package in.hocg.rabbit.com.api.enums;

import in.hocg.boot.utils.annotation.UseDataDictKey;
import in.hocg.boot.utils.enums.DataDictEnum;
import in.hocg.rabbit.com.api.named.ComDataDictKeys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by hocgin on 2022/2/13
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
@UseDataDictKey(value = ComDataDictKeys.DOC_TYPE, description = "文档类型")
public enum DocType implements DataDictEnum {
    Text("text", "纯文本"),
    Html("html", "富文本"),
    Json("json", "JSON"),
    Markdown("markdown", "Markdown"),
    ;
    private final String code;
    private final String name;
}
