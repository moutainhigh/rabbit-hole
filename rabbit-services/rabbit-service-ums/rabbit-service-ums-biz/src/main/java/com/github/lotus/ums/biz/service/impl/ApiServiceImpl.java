package com.github.lotus.ums.biz.service.impl;

import com.github.lotus.ums.biz.entity.Api;
import com.github.lotus.ums.biz.mapper.ApiMapper;
import com.github.lotus.ums.biz.mapstruct.ApiMapping;
import com.github.lotus.ums.biz.pojo.ro.SaveApiRo;
import com.github.lotus.ums.biz.service.ApiService;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractServiceImpl;
import in.hocg.boot.utils.ValidUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * <p>
 * [权限模块] 接口表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-19
 */
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class ApiServiceImpl extends AbstractServiceImpl<ApiMapper, Api> implements ApiService {
    private final ApiMapping mapping;

    @Override
    public Long insertOne(SaveApiRo ro) {
        Long userId = ro.getUserId();
        LocalDateTime now = LocalDateTime.now();

        Api entity = mapping.asApi(ro);
        entity.setCreatedAt(now);
        entity.setCreator(userId);
        validInsert(entity);
        return entity.getId();
    }

    @Override
    public void updateOne(Long id, SaveApiRo ro) {
        Long userId = ro.getUserId();
        LocalDateTime now = LocalDateTime.now();

        Api update = mapping.asApi(ro);
        update.setId(id);
        update.setLastUpdater(userId);
        update.setLastUpdatedAt(now);
        validUpdateById(update);
    }

    @Override
    public void deleteOne(Long id) {
        Api api = getById(id);
        ValidUtils.isFalse(api.getIsPersist(), "该接口为保留接口");
        removeById(id);
    }

    private boolean hasApiTitle(String title, Long... ignoreId) {
        return has(Api::getTitle, title, Api::getId, ignoreId);
    }

    private boolean hasApiEncoding(String encoding, Long... ignoreId) {
        return has(Api::getEncoding, encoding, Api::getId, ignoreId);
    }

    @Override
    public void validEntity(Api entity) {
        Long id = entity.getId();
        String title = entity.getTitle();
        String encoding = entity.getEncoding();
        if (Objects.nonNull(title)) {
            ValidUtils.isFalse(hasApiTitle(title, id), "接口名称已存在");
        }

        if (Objects.nonNull(encoding)) {
            ValidUtils.isFalse(hasApiEncoding(encoding, id), "接口编码已存在");
        }
    }
}
