package com.github.lotus.chaos.biz.support.lbs;

import com.github.lotus.chaos.api.pojo.vo.AMapDistrictVo;
import com.github.lotus.chaos.biz.pojo.dto.IpAndAddressDto;

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
