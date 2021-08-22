package com.github.lotus.mall.biz.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.github.lotus.mall.biz.entity.OrderRefundApply;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.github.lotus.mall.biz.pojo.ro.OrderRefundApplyPagingRo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.aspectj.weaver.ast.Or;

/**
 * <p>
 * [订单模块] 退货申请 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2021-08-21
 */
@Mapper
public interface OrderRefundApplyMapper extends BaseMapper<OrderRefundApply> {

    IPage<OrderRefundApply> paging(@Param("ro") OrderRefundApplyPagingRo ro, @Param("ofPage") Page<Object> ofPage);
}
