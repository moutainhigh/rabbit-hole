package in.hocg.rabbit.bmw.biz.mapstruct;

import in.hocg.rabbit.bmw.biz.entity.PayRecord;
import in.hocg.rabbit.bmw.biz.pojo.dto.CreatePayRecordDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Created by hocgin on 2021/8/14
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Mapper(componentModel = "spring")
public interface PayRecordMapping {
    @Mapping(target = "lastUpdater", ignore = true)
    @Mapping(target = "lastUpdatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "creator", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    PayRecord asPayRecord(CreatePayRecordDto dto);
}
