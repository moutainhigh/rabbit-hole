package com.github.lotus.com.biz.mapper;

import com.github.lotus.com.biz.entity.CommentUserAction;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * [通用模块] 用户的评论行为表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2021-09-05
 */
@Mapper
public interface CommentUserActionMapper extends BaseMapper<CommentUserAction> {

}
