package in.hocg.rabbit.rcm.biz.service;

import in.hocg.rabbit.rcm.biz.entity.PostCategory;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractService;
import in.hocg.rabbit.rcm.biz.pojo.ro.PostCategoryCompleteRo;
import in.hocg.rabbit.rcm.biz.pojo.vo.PostCategoryOrdinaryVo;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * [内容模块] 帖文类目表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2022-04-16
 */
public interface PostCategoryService extends AbstractService<PostCategory> {

    List<PostCategoryOrdinaryVo> listMain();

    Optional<PostCategory> getByEncoding(String encoding);

    List<PostCategoryOrdinaryVo> complete(PostCategoryCompleteRo ro);
}
