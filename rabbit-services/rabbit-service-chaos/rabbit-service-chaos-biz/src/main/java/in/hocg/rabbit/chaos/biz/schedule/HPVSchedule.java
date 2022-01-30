package in.hocg.rabbit.chaos.biz.schedule;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.lang.Pair;
import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import in.hocg.boot.utils.struct.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.util.Lists;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * Created by hocgin on 2022/1/29
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Slf4j
@Component
public class HPVSchedule {
    public static final String tk = "wxtoken:6831522813793b789e711158673288cf_293f66c3441d025f4ad6a752eb42b281";
    public static final String st = "d2fc97cc240ea12f6472b5bd74aeeb49";


    @Scheduled(cron = "0 0/1 0/1 * * ?")
    public void task() {
        LocalDateTime now = LocalDateTime.now();
        List<LocalDateTime> month = List.of(now, now.plusMonths(1), now.plusMonths(2), now.plusMonths(3));

        List<String> dateList = Lists.newArrayList();
        for (LocalDateTime dateTime : month) {
            List<String> dateListItem = queryDateList(dateTime.format(DateTimeFormatter.ISO_LOCAL_DATE));
            dateList.addAll(dateListItem);
        }
        if (dateList.isEmpty()) {
            log.info("[HPV] 没有可查询日期");
            return;
        }
        List<Pair<String, Integer>> result = queryMaxSum(dateList);
        if (result.isEmpty()) {
            log.info("[HPV] 没有可预约日期");
            return;
        }
        log.info("[HPV] 有可预约日期");
    }


    public List<String> queryDateList(String month) {
        String url = StrUtil.format("https://wx.scmttec.com/order/subscribe/workDaysByMonth.do?depaCode=3505810815&linkmanId=25142551&vaccCode=8803&vaccIndex=1&departmentVaccineId=31089&month={}", month);

        HttpRequest queryRequest = HttpUtil.createGet(url)
            .header("tk", tk)
            .header("st", st);
        HttpResponse queryResponse = queryRequest.execute();
        JSONObject queryResult = JSONUtil.parseObj(queryResponse.body());
        JSONObject data = queryResult.getJSONObject("data");
        if (Objects.isNull(data)) {
            return Collections.emptyList();
        }
        JSONArray dateList = data.getJSONArray("dateList");
        return dateList.toList(String.class).stream()
            .map(s -> StrUtil.replace(s, "-", ""))
            .collect(Collectors.toList());
    }

    public List<Pair<String, Integer>> queryMaxSum(List<String> dateList) {
        String url = StrUtil.format("https://wx.scmttec.com/subscribe/subscribe/findSubscribeAmountByDays.do?depaCode=3505810815&vaccCode=8803&vaccIndex=1&days={}&departmentVaccineId=31089",
            String.join(",", dateList));
        HttpRequest queryRequest = HttpUtil.createGet(url)
            .header("tk", tk)
            .header("st", st);
        HttpResponse response = queryRequest.execute();
        JSONObject result = JSONUtil.parseObj(response.body());
        JSONArray data = result.getJSONArray("data");
        if (data.isEmpty()) {
            return Collections.emptyList();
        }
        return data.stream().map(o -> ((JSONObject) o))
            .filter(jsonObject -> jsonObject.getInt("maxSub", 0) > 0)
            .map(jsonObject -> new Pair<>(jsonObject.getStr("day"), jsonObject.getInt("maxSub", 0)))
            .collect(Collectors.toList());
    }
}
