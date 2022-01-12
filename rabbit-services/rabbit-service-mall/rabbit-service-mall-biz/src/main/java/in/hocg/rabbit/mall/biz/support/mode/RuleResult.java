package in.hocg.rabbit.mall.biz.support.mode;

import cn.hutool.core.collection.CollUtil;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Created by hocgin on 2022/1/16
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
@RequiredArgsConstructor
public class RuleResult {
    /**
     * 符合
     */
    private final List<OrderSupport.Item> conform;
    /**
     * 不可用原因
     */
    private final String reason;
    /**
     * 规则
     */
    private Rule rule;

    public boolean isOk() {
        return CollUtil.isNotEmpty(conform);
    }
}
