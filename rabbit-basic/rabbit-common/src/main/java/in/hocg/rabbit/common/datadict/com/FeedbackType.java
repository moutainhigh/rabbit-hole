package in.hocg.rabbit.common.datadict.com;

import in.hocg.boot.utils.enums.DataDictEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;

/**
 * Created by hocgin on 2021/1/10
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
public enum FeedbackType implements DataDictEnum {
    Issues("issues", "问题"),
    Propose("propose", "建议");
    private final Serializable code;
    private final String name;
}
