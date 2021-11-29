package in.hocg.rabbit.bmw.biz.service;

import in.hocg.rabbit.bmw.biz.entity.PayScene;
import in.hocg.rabbit.bmw.biz.pojo.vo.PaySceneItemVo;
import in.hocg.boot.mybatis.plus.autoconfiguration.AbstractService;

import java.util.List;
import java.util.Optional;

/**
 * <p>
 * [支付模块] 支付场景表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2021-08-14
 */
public interface PaySceneService extends AbstractService<PayScene> {

    Optional<PayScene> getByAccessMchIdAndEncoding(Long accessMchId, String sceneCode);

    List<PaySceneItemVo> listBySceneCodeAndAccessMchId(String sceneCode, Long accessMchId);

    List<PaySceneItemVo> listByPaySceneId(Long paySceneId);
}
