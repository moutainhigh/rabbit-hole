package in.hocg.rabbit.rcm.biz.service.impl;

import in.hocg.boot.mybatis.plus.autoconfiguration.core.enhance.convert.UseConvert;
import in.hocg.rabbit.rcm.biz.convert.PostCategoryConvert;
import in.hocg.rabbit.rcm.biz.entity.PostCategory;
import in.hocg.rabbit.rcm.biz.mapper.PostCategoryMapper;
import in.hocg.rabbit.rcm.biz.pojo.ro.PostCategoryCompleteRo;
import in.hocg.rabbit.rcm.biz.pojo.vo.PostCategoryOrdinaryVo;
import in.hocg.rabbit.rcm.biz.service.PostCategoryService;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * [内容模块] 帖文类目表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2022-04-16
 */
@Service
@UseConvert(PostCategoryConvert.class)
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class PostCategoryServiceImpl extends AbstractServiceImpl<PostCategoryMapper, PostCategory> implements PostCategoryService {

    @Override
    public List<PostCategoryOrdinaryVo> listMain() {
        return new ArrayList<>(as(list(), PostCategoryOrdinaryVo.class));
    }

    @Override
    public Optional<PostCategory> getByEncoding(String encoding) {
        return lambdaQuery().eq(PostCategory::getEncoding, encoding).oneOpt();
    }

    @Override
    public List<PostCategoryOrdinaryVo> complete(PostCategoryCompleteRo ro) {
        return as(baseMapper.complete(ro, ro.ofPage()), PostCategoryOrdinaryVo.class).getRecords();
    }
}
