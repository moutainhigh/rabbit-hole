package in.hocg.rabbit.rcm.api.enums;

import in.hocg.boot.utils.annotation.UseDataDictKey;
import in.hocg.boot.utils.enums.DataDictEnum;
import in.hocg.rabbit.rcm.api.named.RcmDataDictKeys;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * Created by hocgin on 2022/2/13
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
@UseDataDictKey(value = RcmDataDictKeys.DOC_TYPE, description = "文档类型")
public enum DocType implements DataDictEnum {
    Text("text", "纯文本"),
    Html("html", "富文本"),
    Rich("rich", "富文本"),
    Markdown("markdown", "Markdown"),
    ;
    private final String code;
    private final String name;
}
