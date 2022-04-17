package in.hocg.rabbit.rcm.biz.convert;

import in.hocg.rabbit.rcm.biz.entity.PostCategory;
import in.hocg.rabbit.rcm.biz.mapstruct.PostCategoryMapping;
import in.hocg.rabbit.rcm.biz.pojo.vo.PostCategoryOrdinaryVo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Created by hocgin on 2022/4/16
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class PostCategoryConvert {
    private final PostCategoryMapping mapping;

    public PostCategoryOrdinaryVo asPostClassifyOrdinaryVo(PostCategory entity) {
        return mapping.asPostClassifyOrdinaryVo(entity);
    }
}
