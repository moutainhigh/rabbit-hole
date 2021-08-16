package com.github.lotus.bmw.biz.mapper;

import com.github.lotus.bmw.biz.entity.PayScene;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lotus.bmw.biz.entity.PaySceneSupport;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * [支付模块] 支付场景表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2021-08-14
 */
@Mapper
public interface PaySceneMapper extends BaseMapper<PayScene> {

    List<PaySceneSupport> listByEncodingAndAccessMchId(@Param("sceneCode") String sceneCode, @Param("accessMchId") Long accessMchId);
}
