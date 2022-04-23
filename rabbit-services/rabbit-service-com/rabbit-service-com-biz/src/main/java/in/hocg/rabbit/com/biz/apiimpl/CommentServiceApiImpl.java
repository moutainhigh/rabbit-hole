package in.hocg.rabbit.com.biz.apiimpl;

import in.hocg.rabbit.com.api.CommentServiceApi;
import in.hocg.rabbit.com.api.pojo.vo.CommentSummaryVo;
import in.hocg.rabbit.com.biz.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by hocgin on 2022/4/17
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class CommentServiceApiImpl implements CommentServiceApi {
    private final CommentService service;

    @Override
    public CommentSummaryVo getSummary(String refType, Long refId, Integer limit) {
        return service.getSummary(refType, refId, limit);
    }
}
