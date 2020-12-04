package com.github.lotus.chaos.module.com.mapper;

import com.github.lotus.chaos.module.com.entity.File;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.io.Serializable;
import java.util.List;

/**
 * <p>
 * [基础模块] 文件引用表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2020-11-11
 */
@Mapper
public interface FileMapper extends BaseMapper<File> {

    List<File> listFileByRelTypeAndRelIdOrderBySortDescAndCreatedAtDesc(@Param("relType") Serializable code, @Param("relId") Long relId);
}
