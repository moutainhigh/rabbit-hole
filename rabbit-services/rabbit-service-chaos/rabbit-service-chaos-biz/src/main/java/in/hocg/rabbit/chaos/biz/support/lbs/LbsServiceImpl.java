package in.hocg.rabbit.chaos.biz.support.lbs;

import in.hocg.rabbit.chaos.api.pojo.vo.AMapDistrictVo;
import in.hocg.rabbit.chaos.biz.manager.LbsManager;
import in.hocg.rabbit.chaos.biz.pojo.dto.IpAndAddressDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by hocgin on 2021/1/10
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class LbsServiceImpl implements LbsService {
    private final LbsManager lbsManager;

    @Override
    public List<AMapDistrictVo> listDistrict() {
        return lbsManager.listDistrict();
    }

    @Override
    public IpAndAddressDto getAddressByIp(String ip) {
        return lbsManager.getAddressByIp(ip);
    }
}
