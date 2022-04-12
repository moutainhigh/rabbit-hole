package in.hocg.rabbit.owp.biz.service;

import in.hocg.rabbit.owp.api.pojo.vo.ApiRouterVo;
import in.hocg.rabbit.owp.biz.entity.OwpApi;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.struct.basic.AbstractService;

import java.util.List;

/**
 * <p>
 * [开放平台] 接口表 服务类
 * </p>
 *
 * @author hocgin
 * @since 2022-04-10
 */
public interface OwpApiService extends AbstractService<OwpApi> {

    List<ApiRouterVo> listAll();

    Boolean hasAuthority(String appid, String method);
}
