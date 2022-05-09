package in.hocg.rabbit.rcm.biz.mapstruct;

import in.hocg.rabbit.rcm.biz.entity.Post;
import in.hocg.rabbit.rcm.biz.pojo.ro.PostCreateRo;
import in.hocg.rabbit.rcm.biz.pojo.vo.PostOrdinaryVo;
import in.hocg.rabbit.rcm.biz.pojo.vo.PostPublishedVo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2022/4/16
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface PostMapping {
    @Mapping(target = "viewCount", ignore = true)
    @Mapping(target = "likeCount", ignore = true)
    @Mapping(target = "replyCount", ignore = true)
    @Mapping(target = "lastReplyUsers", ignore = true)
    @Mapping(target = "lastReplyAt", ignore = true)
    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "creatorAvatarUrl", ignore = true)
    @Mapping(target = "categoryName", ignore = true)
    @Mapping(target = "tags", ignore = true)
    PostOrdinaryVo asPostOrdinaryVo(Post entity);

    @Mapping(target = "originalLink", ignore = true)
    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "tags", ignore = true)
    @Mapping(target = "enabled", ignore = true)
    @Mapping(target = "docId", ignore = true)
    @Mapping(target = "deleter", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    Post asPost(PostCreateRo ro);

    @Mapping(target = "creatorName", ignore = true)
    @Mapping(target = "creatorAvatarUrl", ignore = true)
    @Mapping(target = "categoryName", ignore = true)
    @Mapping(target = "viewCount", ignore = true)
    @Mapping(target = "tags", ignore = true)
    @Mapping(target = "likeCount", ignore = true)
    @Mapping(target = "content", ignore = true)
    PostPublishedVo asPostPublishedVo(Post entity);
}
