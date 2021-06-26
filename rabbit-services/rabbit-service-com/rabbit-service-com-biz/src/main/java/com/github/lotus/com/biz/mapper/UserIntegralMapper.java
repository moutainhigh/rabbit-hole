package com.github.lotus.com.biz.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.lotus.com.biz.entity.UserIntegral;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lotus.com.biz.pojo.ro.MinaIntegralFlowPageRo;
import com.github.lotus.com.biz.pojo.vo.MinaIntegralFlowVo;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 * [通用] 用户积分表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2021-06-26
 */
@Mapper
public interface UserIntegralMapper extends BaseMapper<UserIntegral> {

}
