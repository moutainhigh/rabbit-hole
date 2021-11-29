package in.hocg.rabbit.bmw.biz.service;

import in.hocg.rabbit.bmw.api.pojo.vo.AccessMchComplexVo;
import in.hocg.rabbit.bmw.biz.entity.AccessMch;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

/**
 * <p>
 * [支付模块] 接入商户表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-14
 */
public interface AccessMchService extends AbstractService<AccessMch> {

    Optional<AccessMch> getByEncoding(String encoding);

    boolean checkSupportPayType(Long accessMchId, String payType);

    List<AccessMchComplexVo> listComplexById(Collection<Long> id);
}
