package com.github.lotus.com.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.lotus.com.biz.entity.UserIntegralFlow;
import com.github.lotus.com.biz.pojo.ro.MinaIntegralFlowPageRo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * [通用] 用户积分流水表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2021-06-26
 */
@Mapper
public interface UserIntegralFlowMapper extends BaseMapper<UserIntegralFlow> {

    IPage<UserIntegralFlow> pageFlow(@Param("ro") MinaIntegralFlowPageRo ro, @Param("ofPage") Page ofPage);
}
