package in.hocg.rabbit.com.biz.mapper;

import in.hocg.rabbit.com.biz.entity.PersonalMessage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * [消息模块] 私信消息表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2021-03-21
 */
@Mapper
public interface PersonalMessageMapper extends BaseMapper<PersonalMessage> {

}
