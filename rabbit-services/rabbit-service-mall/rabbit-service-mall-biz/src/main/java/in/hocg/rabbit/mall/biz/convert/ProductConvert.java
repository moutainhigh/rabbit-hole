package in.hocg.rabbit.mall.biz.convert;

import cn.hutool.json.JSONUtil;
import com.alibaba.fastjson.JSON;
import in.hocg.boot.web.datastruct.KeyValue;
import in.hocg.rabbit.mall.biz.entity.Product;
import in.hocg.rabbit.mall.biz.mapstruct.ProductMapping;
import in.hocg.rabbit.mall.biz.pojo.vo.ProductComplexVo;
import in.hocg.rabbit.mall.biz.pojo.vo.ProductOrdinaryVo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by hocgin on 2022/1/12
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ProductConvert {
    private final ProductMapping mapping;

    public ProductComplexVo asProductComplexVo(Product entity) {
        String attrsStr = entity.getAttrs();
        return mapping.asProductComplexVo(entity)
            .setAttrs(JSON.parseArray(attrsStr, KeyValue.class));
    }

    public ProductOrdinaryVo asProductOrdinaryVo(Product entity) {
        String attrsStr = entity.getAttrs();
        return mapping.asProductOrdinaryVo(entity)
            .setAttrs(JSON.parseArray(attrsStr, KeyValue.class));
    }

}
