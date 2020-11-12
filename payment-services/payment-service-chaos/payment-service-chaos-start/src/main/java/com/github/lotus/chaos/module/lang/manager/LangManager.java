package com.github.lotus.chaos.module.lang.manager;

import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.github.lotus.chaos.module.lang.pojo.dto.AMapDistrictDto;
import com.github.lotus.chaos.module.lang.pojo.dto.AMapDistrictResultDto;
import com.github.lotus.chaos.module.lang.pojo.dto.IpAndAddressDto;
import in.hocg.boot.web.exception.ServiceException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

/**
 * Created by hocgin on 2020/11/11
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class LangManager {
    private final RestTemplate restTemplate;
    private final RedisManager redisManager;

    /**
     * 根据IP获取地址
     *
     * @param ip
     * @return
     */
    public IpAndAddressDto getAddressByIp(String ip) {
        String token = "34579df219c0eadf6c9f02f610c8169b";
        String url = String.format("http://api.ip138.com/query/?ip=%s&token=%s&datatype=jsonp", ip, token);
        return restTemplate.getForObject(url, IpAndAddressDto.class);
    }

    /**
     * 获取高德的城市列表信息
     *
     * @return
     */
    public List<AMapDistrictDto> getDistrict() {
        String token = "a17f4063f58d7fc70de9a205e22f2450";
        String subdistrict = "3";
        String url = String.format("https://restapi.amap.com/v3/config/district?key=%s&subdistrict=%s", token, subdistrict);
        String resultText = HttpUtil.get(url);
        final AMapDistrictResultDto result = JSON.parseObject(resultText, AMapDistrictResultDto.class);
        if (Objects.isNull(result) || !result.isOk()) {
            throw ServiceException.wrap("请求高德数据失败");
        }
        return result.getDistricts();
    }

}
