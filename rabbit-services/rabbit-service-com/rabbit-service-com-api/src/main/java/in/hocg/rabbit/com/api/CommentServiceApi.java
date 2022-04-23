package in.hocg.rabbit.com.api;

import in.hocg.rabbit.com.api.pojo.vo.CommentSummaryVo;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * Created by hocgin on 2022/4/17
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@FeignClient(value = ComServiceName.NAME)
public interface CommentServiceApi {
    String CONTEXT_ID = "CommentServiceApi";

    /**
     * 获取最新评论
     *
     * @param limit 最新几条
     * @return
     */
    @PostMapping(value = CONTEXT_ID + "/getSummary", headers = ComServiceName.FEIGN_HEADER)
    CommentSummaryVo getSummary(@RequestParam("refType") String refType, @RequestParam("refId") Long refId,
                                @RequestParam("limit") Integer limit);

}
