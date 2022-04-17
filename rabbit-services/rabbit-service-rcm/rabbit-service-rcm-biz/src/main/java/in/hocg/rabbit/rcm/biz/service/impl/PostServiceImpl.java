package in.hocg.rabbit.rcm.biz.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.enhance.convert.UseConvert;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.vo.IScroll;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.utils.PageUtils;
import in.hocg.rabbit.rcm.api.enums.DocType;
import in.hocg.rabbit.rcm.api.pojo.ro.PublishDocTextRo;
import in.hocg.rabbit.rcm.biz.convert.PostConvert;
import in.hocg.rabbit.rcm.biz.entity.Post;
import in.hocg.rabbit.rcm.biz.entity.PostCategory;
import in.hocg.rabbit.rcm.biz.mapper.PostMapper;
import in.hocg.rabbit.rcm.biz.mapstruct.PostMapping;
import in.hocg.rabbit.rcm.biz.pojo.ro.PostCreateRo;
import in.hocg.rabbit.rcm.biz.pojo.ro.PostScrollRo;
import in.hocg.rabbit.rcm.biz.pojo.vo.PostOrdinaryVo;
import in.hocg.rabbit.rcm.biz.service.DocService;
import in.hocg.rabbit.rcm.biz.service.PostCategoryService;
import in.hocg.rabbit.rcm.biz.service.PostService;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * [内容模块] 帖文表 服务实现类
 * </p>
 *
 * @author hocgin
 * @since 2022-04-16
 */
@Service
@UseConvert(PostConvert.class)
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class PostServiceImpl extends AbstractServiceImpl<PostMapper, Post> implements PostService {
    private final PostMapping mapping;
    private final DocService docService;
    private final PostCategoryService postCategoryService;

    @Override
    public IScroll<PostOrdinaryVo> scroll(PostScrollRo ro) {
        String category = ro.getCategory();
        List<String> customCategory = Lists.newArrayList("recommend", "hot");
        if (StrUtil.isNotBlank(category) && !customCategory.contains(category)) {
            PostCategory postCategory = postCategoryService.getByEncoding(category)
                .orElseThrow(() -> new IllegalArgumentException("分类不存在"));
            ro.setCategoryId(postCategory.getId());
        }
        return as(PageUtils.fillScroll(baseMapper.scroll(ro, ro.ofPage()), Post::getId), PostOrdinaryVo.class);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long create(PostCreateRo ro) {
        Post entity = mapping.asPost(ro);
        entity.setTags(CollUtil.join(ro.getTags(), ","));
        entity.setEnabled(true);
        saveOrUpdate(entity);
        PublishDocTextRo docTextRo = new PublishDocTextRo();
        docTextRo.setContent(ro.getContent());
        docTextRo.setDoctype(DocType.Rich.getCode());
        docTextRo.setPublished(!entity.getDrafted());
        docTextRo.setDocId(entity.getId());
        docService.publishContent(docTextRo);
        return entity.getId();
    }

}
