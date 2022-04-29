package in.hocg.rabbit.rcm.biz.convert;

import in.hocg.rabbit.com.api.CommentServiceApi;
import in.hocg.rabbit.com.api.pojo.vo.CommentSummaryVo;
import in.hocg.rabbit.common.datadict.common.RefType;
import in.hocg.rabbit.common.utils.DbUtils;
import in.hocg.rabbit.rcm.biz.entity.Post;
import in.hocg.rabbit.rcm.biz.mapstruct.PostMapping;
import in.hocg.rabbit.rcm.biz.pojo.vo.PostOrdinaryVo;
import in.hocg.rabbit.rcm.biz.pojo.vo.PostSummaryVo;
import in.hocg.rabbit.rcm.biz.pojo.vo.PostPublishedVo;
import in.hocg.rabbit.rcm.biz.pojo.vo.PublishedDocVo;
import in.hocg.rabbit.rcm.biz.service.DocService;
import in.hocg.rabbit.rcm.biz.service.PostService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Created by hocgin on 2022/4/16
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class PostConvert {
    private final PostMapping mapping;
    private final CommentServiceApi commentServiceApi;
    private final DocService docService;

    public PostOrdinaryVo asPostOrdinaryVo(Post entity) {
        Long postId = entity.getId();
        CommentSummaryVo commentSummary = commentServiceApi.getSummary(RefType.Post.getCodeStr(), postId, 3);
        CommentSummaryVo.LastCommentVo lastReply = commentSummary.getLastReply();
        List<CommentSummaryVo.LastCommentVo> lastReplyList = commentSummary.getLastReplyList();
        LocalDateTime lastReplyAt = null;
        if (Objects.nonNull(lastReply)) {
            lastReplyAt = lastReply.getCreatedAt();
        }
        Optional<PostSummaryVo> summary = docService.getSummary(RefType.Post.getCodeStr(), postId);

        List<String> tags = DbUtils.toList(entity.getTags());
        List<PostOrdinaryVo.ReplyUser> lastReplyUsers = lastReplyList.stream()
            .map(replyUser -> new PostOrdinaryVo.ReplyUser().setReplyUserId(replyUser.getCreator()))
            .collect(Collectors.toList());
        return mapping.asPostOrdinaryVo(entity)
            .setLastReplyUsers(lastReplyUsers)
            .setLikeCount(summary.map(PostSummaryVo::getLikeCount).orElse(0L))
            .setViewCount(summary.map(PostSummaryVo::getViewCount).orElse(0L))
            .setReplyCount(commentSummary.getTotalReply())
            .setLastReplyAt(lastReplyAt)
            .setTags(tags);
    }

    public PostPublishedVo asPostPublishedVo(Post entity) {
        PostPublishedVo result = mapping.asPostPublishedVo(entity);
        Long docId = entity.getDocId();
        if (Objects.isNull(docId)) {
            return null;
        }
        result.setTags(DbUtils.toList(entity.getTags()));

        PublishedDocVo doc = docService.getPublishedById(docId);
        if (Objects.nonNull(doc)) {
            result.setContent(doc.getContent());
            result.setViewCount(doc.getViewCount());
            result.setLikeCount(doc.getLikeCount());
        }
        return result;
    }
}
