package com.github.lotus.mina.biz.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.lotus.mina.biz.entity.GameCard;
import com.github.lotus.mina.biz.pojo.ro.GameCardPageRo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * [小程序模块] 游戏表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2021-01-01
 */
@Mapper
public interface GameCardMapper extends BaseMapper<GameCard> {

    IPage<GameCard> paging(@Param("ro") GameCardPageRo ro, @Param("ofPage") Page<?> ofPage);
}
