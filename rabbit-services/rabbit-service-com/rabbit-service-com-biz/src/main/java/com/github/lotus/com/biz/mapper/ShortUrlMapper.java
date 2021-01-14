package com.github.lotus.com.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.lotus.com.biz.entity.ShortUrl;
import com.github.lotus.com.biz.pojo.ro.ShortUrlPagingRo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * [基础模块] 短链接表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2021-01-14
 */
@Mapper
public interface ShortUrlMapper extends BaseMapper<ShortUrl> {

    IPage<ShortUrl> paging(@Param("ro") ShortUrlPagingRo ro, @Param("ofPage") Page<Object> ofPage);
}
