package in.hocg.rabbit.bmw.biz.mapper;

import in.hocg.rabbit.bmw.biz.entity.PaymentMch;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Optional;

/**
 * <p>
 * [支付模块] 支付商户表 Mapper 接口
 * </p>
 *
 * @author hocgin
 * @since 2021-08-14
 */
@Mapper
public interface PaymentMchMapper extends BaseMapper<PaymentMch> {

    Optional<PaymentMch> getByAccessMchIdAndSceneCodeAndPayType(@Param("accessMchId") Long accessMchId, @Param("scenesCode") String scenesCode, @Param("payType") String payType);

    Optional<PaymentMch> getByAccessMchIdAndPaySceneIdAndPayType(@Param("accessMchId") Long accessMchId, @Param("paySceneId") Long paySceneId, @Param("payType") String payType);
}
