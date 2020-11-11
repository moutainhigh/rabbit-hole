package com.github.lotus.chaos.module.com.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lotus.chaos.module.com.entity.District;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * [基础模块] 城市规划表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2020-11-11
 */
@Mapper
public interface DistrictMapper extends BaseMapper<District> {

    List<District> getChildrenDistrictByAdcode(@Param("adcode") String adcode);
}
