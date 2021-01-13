package com.github.lotus.com.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.com.biz.entity.Comment;
import com.github.lotus.com.biz.pojo.ro.ChildCommentPagingRo;
import com.github.lotus.com.biz.pojo.ro.CommentInsertRo;
import com.github.lotus.com.biz.pojo.ro.CommentUpdateRo;
import com.github.lotus.com.biz.pojo.ro.RootCommentPagingRo;
import com.github.lotus.com.biz.pojo.vo.CommentComplexVo;
import com.github.lotus.com.biz.pojo.vo.RootCommentComplexVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

/**
 * <p>
 * [通用模块] 评论表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-01-13
 */
public interface CommentService extends AbstractService<Comment> {

    /**
     * 更新评论(状态或内容)
     *
     * @param ro
     */
    void updateOne(Long id, CommentUpdateRo ro);

    /**
     * 发表评论
     *
     * @param ro ro
     * @throws Throwable
     */
    void insertOne(CommentInsertRo ro);

    /**
     * 查询根级评论
     *
     * @param ro ro
     * @return
     */
    IPage<RootCommentComplexVo> pagingRootComment(RootCommentPagingRo ro);

    /**
     * 查询根评论的子评论
     *
     * @param ro 父级评论ID
     * @return
     */
    IPage<CommentComplexVo> pagingChildComment(ChildCommentPagingRo ro);
}
