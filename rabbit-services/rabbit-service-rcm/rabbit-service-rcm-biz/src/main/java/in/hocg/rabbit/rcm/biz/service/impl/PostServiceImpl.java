package in.hocg.rabbit.rcm.biz.service.impl;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.google.common.collect.Lists;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.enhance.convert.UseConvert;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.vo.IScroll;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.utils.PageUtils;
import in.hocg.rabbit.common.datadict.common.RefType;
import in.hocg.rabbit.common.utils.DbUtils;
import in.hocg.rabbit.rcm.api.enums.DocType;
import in.hocg.rabbit.rcm.api.pojo.ro.CreateDocRo;
import in.hocg.rabbit.rcm.api.pojo.ro.PublishDocTextRo;
import in.hocg.rabbit.rcm.biz.convert.PostConvert;
import in.hocg.rabbit.rcm.biz.entity.Post;
import in.hocg.rabbit.rcm.biz.entity.PostCategory;
import in.hocg.rabbit.rcm.biz.mapper.PostMapper;
import in.hocg.rabbit.rcm.biz.mapstruct.PostMapping;
import in.hocg.rabbit.rcm.biz.pojo.ro.PostCreateRo;
import in.hocg.rabbit.rcm.biz.pojo.ro.PostScrollRo;
import in.hocg.rabbit.rcm.biz.pojo.vo.PostOrdinaryVo;
import in.hocg.rabbit.rcm.biz.pojo.vo.PostPublishedVo;
import in.hocg.rabbit.rcm.biz.service.DocService;
import in.hocg.rabbit.rcm.biz.service.PostCategoryService;
import in.hocg.rabbit.rcm.biz.service.PostService;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.context.annotation.Lazy;
import lombok.RequiredArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

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
        List<String> tags = CollUtil.defaultIfEmpty(CollUtil.sub(ro.getTags(), 0, 4), List.of("讨论"));

        entity.setTags(DbUtils.toString(tags));
        entity.setEnabled(true);
        saveOrUpdate(entity);

        CreateDocRo createDocRo = new CreateDocRo();
        createDocRo.setRefId(entity.getId());
        createDocRo.setRefType(RefType.Post.getCodeStr());
        createDocRo.setOwnerUserId(ro.getUserId());
        Long docId = docService.createDoc(createDocRo);

        PublishDocTextRo docTextRo = new PublishDocTextRo();
        docTextRo.setTitle(ro.getTitle());
        docTextRo.setContent(ro.getContent());
        docTextRo.setDoctype(DocType.Html.getCode());
        docTextRo.setPublished(!entity.getDrafted());
        docTextRo.setDocId(docId);
        docService.publishContent(docTextRo);

        Post update = new Post();
        update.setId(entity.getId());
        update.setDocId(docId);
        saveOrUpdate(update);
        return entity.getId();
    }

    @Override
    public PostPublishedVo getPostVoById(Long id) {
        Post entity = getById(id);
        return as(entity, PostPublishedVo.class);
    }

}
