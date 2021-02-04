package com.github.lotus.mina.biz.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.lotus.mina.biz.entity.StatusMaterial;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lotus.mina.biz.pojo.ro.MinaStatusMaterialPagingRo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * [小程序模块] 状态素材表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2021-02-05
 */
@Mapper
public interface StatusMaterialMapper extends BaseMapper<StatusMaterial> {

    IPage<StatusMaterial> pagingForMina(@Param("ro") MinaStatusMaterialPagingRo ro, @Param("ofPage") Page<Object> ofPage);
}
