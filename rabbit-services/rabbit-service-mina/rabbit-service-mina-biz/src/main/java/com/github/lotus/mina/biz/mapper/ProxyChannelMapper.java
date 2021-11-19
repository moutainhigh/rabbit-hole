package com.github.lotus.mina.biz.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.lotus.mina.biz.entity.ProxyChannel;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lotus.mina.biz.pojo.ro.ProxyChannelPagingRo;
import com.github.lotus.mina.biz.pojo.vo.ProxyChannelOrdinaryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * [代理] 用户隧道表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2021-11-16
 */
@Mapper
public interface ProxyChannelMapper extends BaseMapper<ProxyChannel> {

    IPage<ProxyChannel> paging(@Param("ro") ProxyChannelPagingRo ro, Page a);
}
