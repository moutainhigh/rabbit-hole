package in.hocg.rabbit.mina.biz.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import in.hocg.rabbit.mina.biz.entity.RechargeOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import in.hocg.rabbit.mina.biz.pojo.ro.RechargeOrderPageRo;
import in.hocg.rabbit.mina.biz.pojo.vo.RechargeOrderOrdinaryVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * [功能模块] 充值单据表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2022-04-07
 */
@Mapper
public interface RechargeOrderMapper extends BaseMapper<RechargeOrder> {

    IPage<RechargeOrder> paging(@Param("ro") RechargeOrderPageRo ro, Page<Object> ofPage);
}
