package in.hocg.rabbit.rcm.biz.convert;

import cn.hutool.core.util.StrUtil;
import in.hocg.rabbit.rcm.biz.entity.Post;
import in.hocg.rabbit.rcm.biz.mapstruct.PostMapping;
import in.hocg.rabbit.rcm.biz.pojo.vo.PostOrdinaryVo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.List;

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

    public PostOrdinaryVo asPostOrdinaryVo(Post entity) {
        List<String> tags = StrUtil.split(entity.getTags(), ',', true, true);
        return mapping.asPostOrdinaryVo(entity)
            .setTags(tags);
    }
}
