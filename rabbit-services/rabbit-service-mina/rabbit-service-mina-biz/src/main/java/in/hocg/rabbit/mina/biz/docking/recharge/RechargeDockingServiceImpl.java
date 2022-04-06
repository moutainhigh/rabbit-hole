package in.hocg.rabbit.mina.biz.docking.recharge;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;
import in.hocg.boot.mybatis.plus.extensions.httplog.support.HttpLogUtils;
import in.hocg.boot.utils.LogUtils;
import in.hocg.boot.utils.exception.ServiceException;
import in.hocg.rabbit.mina.biz.docking.recharge.pojo.ro.BaseRo;
import in.hocg.rabbit.mina.biz.docking.recharge.pojo.ro.CheckRechargeRo;
import in.hocg.rabbit.mina.biz.docking.recharge.pojo.ro.RechargeRo;
import in.hocg.rabbit.mina.biz.docking.recharge.pojo.vo.CheckRechargeVo;
import in.hocg.rabbit.mina.biz.docking.recharge.pojo.vo.RechargeVo;
import in.hocg.rabbit.mina.biz.docking.recharge.pojo.vo.ResultVo;
import in.hocg.rabbit.mina.biz.support.recharge.RechargeHelper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * Created by hocgin on 2022/4/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class RechargeDockingServiceImpl implements RechargeDockingService {

    @Override
    public RechargeVo recharge(RechargeRo ro) {
        // 不要去掉这部分范型
        return send(ro, "/yrapi.php/index/recharge", new TypeReference<RechargeVo>() {
        });
    }

    @Override
    public List<CheckRechargeVo> checkRecharge(CheckRechargeRo ro) {
        // 不要去掉这部分范型
        return send(ro, "/yrapi.php/index/check", new TypeReference<List<CheckRechargeVo>>() {
        });
    }

    private <T> T send(BaseRo ro, String url, TypeReference<T> type) {
        String baseUrl = "http://gzh.beehost.cn";
        JSONObject params = JSON.parseObject(JSON.toJSONString(ro));
        ro.setSign(RechargeHelper.getSign(params));
        params.put("sign", ro.getSign());
        HttpRequest request = HttpUtil.createPost(StrUtil.format("{}{}", baseUrl, url))
            .form(params)
            .contentType("application/x-www-form-urlencoded;charset=utf-8");
        HttpResponse resp = LogUtils.logAsync(request::execute, HttpLogUtils.getAsyncReady(HttpLogUtils.request(request)),
            HttpLogUtils.getDefaultComplete());
        ResultVo<String> result = JSON.parseObject(resp.body(), new TypeReference<>() {
        });

        if (result.isSuccess()) {
            throw ServiceException.wrap(result.getErrmsg());
        }
        return JSON.parseObject(result.getData(), type);
    }
}
