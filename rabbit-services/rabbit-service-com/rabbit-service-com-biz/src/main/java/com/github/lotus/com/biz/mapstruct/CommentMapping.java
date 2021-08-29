package com.github.lotus.com.biz.mapstruct;

import com.github.lotus.com.biz.entity.Comment;
import com.github.lotus.com.biz.pojo.ro.CommentClientRo;
import com.github.lotus.com.biz.pojo.ro.CommentInsertRo;
import com.github.lotus.com.biz.pojo.ro.CommentUpdateRo;
import com.github.lotus.com.biz.pojo.vo.CommentClientVo;
import com.github.lotus.com.biz.pojo.vo.CommentComplexVo;
import com.github.lotus.com.biz.pojo.vo.RootCommentComplexVo;
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

    @Mapping(target = "action", ignore = true)
    @Mapping(target = "replyId", source = "parentId")
    @Mapping(target = "likes", source = "likesCount")
    @Mapping(target = "disliked", ignore = true)
    @Mapping(target = "hasReply", ignore = true)
    @Mapping(target = "replier", ignore = true)
    @Mapping(target = "author", ignore = true)
    CommentClientVo asCommentClientVo(Comment entity);

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
}
