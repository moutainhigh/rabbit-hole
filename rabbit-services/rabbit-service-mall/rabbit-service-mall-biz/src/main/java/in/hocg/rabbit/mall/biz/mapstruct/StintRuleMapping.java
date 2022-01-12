package in.hocg.rabbit.mall.biz.mapstruct;

import in.hocg.rabbit.mall.biz.entity.StintRule;
import in.hocg.rabbit.mall.biz.pojo.ro.StintRuleSaveRo;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2022/1/19
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface StintRuleMapping {

    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "deleter", ignore = true)
    @Mapping(target = "deletedAt", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    StintRule asStintRule(StintRuleSaveRo ro);
}
