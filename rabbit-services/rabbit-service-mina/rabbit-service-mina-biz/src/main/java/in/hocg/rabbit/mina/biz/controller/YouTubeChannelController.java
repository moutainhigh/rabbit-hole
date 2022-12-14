package in.hocg.rabbit.mina.biz.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.boot.logging.autoconfiguration.core.UseLogger;
import in.hocg.boot.utils.struct.result.Result;
import in.hocg.boot.web.autoconfiguration.utils.web.ResponseUtils;
import in.hocg.rabbit.mina.biz.manager.YouTubeService;
import in.hocg.rabbit.mina.biz.pojo.ro.UploadCollectionRo;
import in.hocg.rabbit.mina.biz.pojo.ro.YouTubeChannelCompleteRo;
import in.hocg.rabbit.mina.biz.pojo.ro.YouTubeChannelPageRo;
import in.hocg.rabbit.mina.biz.pojo.vo.Y2bChannelCompleteVo;
import in.hocg.rabbit.mina.biz.service.Y2bChannelService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by hocgin on 2022/3/31
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Validated
@RestController
@Api(tags = "mina::Y2B.频道")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping("/youtube/channel")
public class YouTubeChannelController {
    private final Y2bChannelService service;

    @UseLogger("搜索 - YouTube 频道")
    @PostMapping("/_complete")
    public Result<List<Y2bChannelCompleteVo>> complete(@RequestBody YouTubeChannelCompleteRo ro) {
        return Result.success(service.complete(ro));
    }

    @UseLogger("分页搜索 - YouTube 频道")
    @PostMapping("/_paging")
    public Result<IPage<Y2bChannelCompleteVo>> paging(@RequestBody YouTubeChannelPageRo ro) {
        return Result.success(service.paging(ro));
    }

    @UseLogger("上传 - YouTube 频道")
    @PostMapping("/{channelId}/collection/upload")
    public Result<Void> uploadCollection(@PathVariable Long channelId, @RequestBody UploadCollectionRo ro) {
        service.uploadCollection(channelId, ro);
        return Result.success();
    }


    @UseLogger("检查授权 - YouTube 频道")
    @GetMapping("/{channelId}/ping")
    public Result<Object> ping(@PathVariable Long channelId) {
        return Result.success(service.ping(channelId));
    }
}
