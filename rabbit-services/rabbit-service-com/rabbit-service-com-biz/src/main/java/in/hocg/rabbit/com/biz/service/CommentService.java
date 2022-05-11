package in.hocg.rabbit.com.biz.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.vo.IScroll;
import in.hocg.rabbit.com.api.pojo.vo.CommentSummaryVo;
import in.hocg.rabbit.com.biz.entity.Comment;
import in.hocg.rabbit.com.biz.pojo.ro.*;
import in.hocg.rabbit.com.biz.pojo.ro.comment.CommentClientScrollRo;
import in.hocg.rabbit.com.biz.pojo.ro.comment.CommentReportRo;
import in.hocg.rabbit.com.biz.pojo.vo.CommentClientVo;
import in.hocg.rabbit.com.biz.pojo.vo.CommentComplexVo;
import in.hocg.rabbit.com.biz.pojo.vo.RootCommentComplexVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractService;

import java.util.List;

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

    CommentClientVo like(CommentLikeRo ro);

    CommentClientVo dislike(CommentDislikeRo ro);

    IPage<CommentComplexVo> paging(CommentPagingRo ro);

    CommentClientVo replyWithClient(CommentClientRo ro);

    IPage<CommentClientVo> pagingWithClient(CommentClientPagingRo ro);

    IScroll<CommentClientVo> scrollWithClient(CommentClientScrollRo ro);

    void report(CommentReportRo ro);

    Boolean hasReply(Long id);

    CommentSummaryVo getSummary(String refType, Long refId, Integer limit);

    List<CommentClientVo> relatedWithClient(Long id, Long userId);
}
