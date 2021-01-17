package com.github.lotus.com.biz.mapper;

import com.github.lotus.com.biz.entity.Feedback;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * [通用模块] 用户反馈表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2021-01-10
 */
@Mapper
public interface FeedbackMapper extends BaseMapper<Feedback> {

}
