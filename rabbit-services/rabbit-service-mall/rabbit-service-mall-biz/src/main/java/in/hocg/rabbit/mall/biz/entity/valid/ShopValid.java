package in.hocg.rabbit.mall.biz.entity.valid;

import cn.hutool.core.lang.Assert;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.enhance.listeners.EntityListener;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.enhance.CommonEntity;
import in.hocg.rabbit.mall.biz.entity.Shop;
import in.hocg.rabbit.mall.biz.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.Objects;

/**
 * Created by hocgin on 2022/1/11
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ShopValid implements EntityListener<Shop> {
    private final ShopService service;

    @Override
    public void onPreInsert(Shop entity) {
        onPreUpdate(entity);
    }

    @Override
    public void onPreUpdate(Shop entity) {
        String encoding = entity.getEncoding();
        if (Objects.nonNull(encoding)) {
            Assert.isFalse(service.has(Shop::getEncoding, encoding, CommonEntity::getId, entity.getId()), "编码已存在");
        }
    }
}
