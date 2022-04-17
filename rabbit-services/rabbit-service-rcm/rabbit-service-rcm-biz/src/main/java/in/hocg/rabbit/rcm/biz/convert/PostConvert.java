package in.hocg.rabbit.rcm.biz.convert;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import in.hocg.rabbit.com.api.CommentServiceApi;
import in.hocg.rabbit.com.api.pojo.vo.LastCommentVo;
import in.hocg.rabbit.common.datadict.common.RefType;
import in.hocg.rabbit.rcm.biz.entity.Post;
import in.hocg.rabbit.rcm.biz.mapstruct.PostMapping;
import in.hocg.rabbit.rcm.biz.pojo.vo.PostOrdinaryVo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.Function;
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

    public PostOrdinaryVo asPostOrdinaryVo(Post entity) {
        List<LastCommentVo> lastReply = commentServiceApi.listLastComment(RefType.Post.getCodeStr(), entity.getId(), 3);
        LocalDateTime lastReplyAt = null;
        if (CollUtil.isNotEmpty(lastReply)) {
            lastReplyAt = lastReply.get(0).getCreatedAt();
        }

        List<String> tags = StrUtil.split(entity.getTags(), ',', true, true);
        return mapping.asPostOrdinaryVo(entity)
            .setLastReplyUsers(lastReply.stream()
                .map(entity1 -> new PostOrdinaryVo.ReplyUser().setReplyUserId(entity1.getCreator()))
                .collect(Collectors.toList()))
            .setLastReplyAt(lastReplyAt)
            .setTags(tags);
    }
}
