package in.hocg.rabbit.mall.biz.convert;

import com.alibaba.fastjson.JSON;
import in.hocg.rabbit.mall.biz.entity.Sku;
import in.hocg.rabbit.mall.biz.mapstruct.SkuMapping;
import in.hocg.rabbit.mall.biz.pojo.vo.SkuComplexVo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Created by hocgin on 2022/1/12
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class SkuConvert {
    private final SkuMapping mapping;

    public SkuComplexVo asSkuComplexVo(Sku entity) {
        SkuComplexVo result = mapping.asSkuComplexVo(entity);
        result.setSpec(JSON.parseArray(entity.getSpecData(), SkuComplexVo.Spec.class));
        return result;
    }
}
