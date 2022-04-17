package in.hocg.rabbit.rcm.biz.mapstruct;

import in.hocg.rabbit.rcm.biz.entity.PostCategory;
import in.hocg.rabbit.rcm.biz.pojo.vo.PostCategoryOrdinaryVo;
import org.mapstruct.Mapper;

/**
 * Created by hocgin on 2022/4/16
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface PostCategoryMapping {
    PostCategoryOrdinaryVo asPostClassifyOrdinaryVo(PostCategory entity);
}
