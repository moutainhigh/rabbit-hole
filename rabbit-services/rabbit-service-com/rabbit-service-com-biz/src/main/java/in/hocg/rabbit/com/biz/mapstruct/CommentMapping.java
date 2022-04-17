package in.hocg.rabbit.com.biz.mapstruct;

import in.hocg.rabbit.com.api.pojo.vo.LastCommentVo;
import in.hocg.rabbit.com.biz.entity.Comment;
import in.hocg.rabbit.com.biz.pojo.ro.CommentClientRo;
import in.hocg.rabbit.com.biz.pojo.ro.CommentInsertRo;
import in.hocg.rabbit.com.biz.pojo.ro.CommentUpdateRo;
import in.hocg.rabbit.com.biz.pojo.vo.CommentClientVo;
import in.hocg.rabbit.com.biz.pojo.vo.CommentComplexVo;
import in.hocg.rabbit.com.biz.pojo.vo.RootCommentComplexVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2020/2/15.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface CommentMapping {

    @Mapping(target = "PCommenter", ignore = true)
    @Mapping(target = "PCommenterId", ignore = true)
    @Mapping(target = "commenterId", source = "entity.creator")
    @Mapping(target = "commenter", ignore = true)
    CommentComplexVo asCommentComplexVo(Comment entity);

    @Mapping(target = "childTotal", ignore = true)
    RootCommentComplexVo asRootCommentComplexVo(CommentComplexVo entity);

    @Mapping(target = "dislikesCount", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "likesCount", ignore = true)
    @Mapping(target = "targetId", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "treePath", ignore = true)
    Comment asComment(CommentInsertRo qo);

    @Mapping(target = "dislikesCount", ignore = true)
    @Mapping(target = "likesCount", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "parentId", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "targetId", ignore = true)
    @Mapping(target = "treePath", ignore = true)
    Comment asComment(CommentUpdateRo ro);

    @Mapping(target = "datetime", source = "createdAt")
    @Mapping(target = "action", ignore = true)
    @Mapping(target = "replyId", source = "parentId")
    @Mapping(target = "likes", source = "likesCount")
    @Mapping(target = "disliked", source = "dislikesCount")
    @Mapping(target = "hasReply", ignore = true)
    @Mapping(target = "replier", ignore = true)
    @Mapping(target = "author", ignore = true)
    CommentClientVo asCommentClientVo(Comment entity);

    @Mapping(target = "dislikesCount", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "treePath", ignore = true)
    @Mapping(target = "targetId", ignore = true)
    @Mapping(target = "parentId", ignore = true)
    @Mapping(target = "likesCount", ignore = true)
    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Comment asComment(CommentClientRo ro);

    LastCommentVo asLastCommentVo(Comment entity);
}
