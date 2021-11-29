package in.hocg.rabbit.chaos.biz.support.lbs;

import in.hocg.rabbit.chaos.api.pojo.vo.AMapDistrictVo;
import in.hocg.rabbit.chaos.biz.pojo.dto.IpAndAddressDto;

import java.util.List;

/**
 * Created by hocgin on 2021/1/10
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
public interface LbsService {

    List<AMapDistrictVo> listDistrict();

    IpAndAddressDto getAddressByIp(String ip);
}
