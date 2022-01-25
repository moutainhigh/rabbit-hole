package in.hocg.rabbit.mall.biz.convert;

import in.hocg.rabbit.mall.biz.entity.StintRule;
import in.hocg.rabbit.mall.biz.mapstruct.StintRuleMapping;
import in.hocg.rabbit.mall.biz.pojo.vo.StintRuleComplexVo;
import in.hocg.rabbit.mall.biz.pojo.vo.StintRuleOrdinaryVo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Created by hocgin on 2022/1/19
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class StintRuleConvert {
    private final StintRuleMapping mapping;

    public StintRuleOrdinaryVo convertStintRuleOrdinaryVo(StintRule entity) {
        return mapping.asStintRuleOrdinaryVo(entity);
    }

    public StintRuleComplexVo convertStintRuleComplexVo(StintRule entity) {
        return mapping.asStintRuleComplexVo(entity);
    }
}
