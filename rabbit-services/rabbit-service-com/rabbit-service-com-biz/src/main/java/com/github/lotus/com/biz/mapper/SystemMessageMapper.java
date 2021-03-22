package com.github.lotus.com.biz.mapper;

import com.github.lotus.com.biz.entity.SystemMessage;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * [消息模块] 系统消息表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2021-03-21
 */
@Mapper
public interface SystemMessageMapper extends BaseMapper<SystemMessage> {

}
