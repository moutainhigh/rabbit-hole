package in.hocg.rabbit.rcm.biz.service;

import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.vo.IScroll;
import in.hocg.rabbit.rcm.biz.entity.Post;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractService;
import in.hocg.rabbit.rcm.biz.pojo.ro.PostCreateRo;
import in.hocg.rabbit.rcm.biz.pojo.ro.PostScrollRo;
import in.hocg.rabbit.rcm.biz.pojo.vo.PostOrdinaryVo;
import in.hocg.rabbit.rcm.biz.pojo.vo.PostPublishedVo;

/**
 * <p>
 * [内容模块] 帖文表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2022-04-16
 */
public interface PostService extends AbstractService<Post> {

    /**
     * 滚动查询
     *
     * @param ro
     * @return
     */
    IScroll<PostOrdinaryVo> scroll(PostScrollRo ro);

    /**
     * 创建贴文
     *
     * @param ro
     * @return
     */
    Long create(PostCreateRo ro);

    /**
     * 查看详情
     *
     * @param id
     * @return
     */
    PostPublishedVo getPostVoById(Long id);
}
