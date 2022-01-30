package in.hocg.rabbit.mina.biz.controller.client;

import com.alibaba.fastjson.JSON;
import in.hocg.boot.utils.struct.result.Result;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.apache.commons.io.FileUtils;
import org.springframework.context.annotation.Lazy;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by hocgin on 2022/2/9
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Api(tags = {"mina::小程序应用", "头像"})
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
@RequestMapping({"/face"})
public class FaceClientController {

    @SneakyThrows
    @GetMapping("/topic")
    public Result<Object> topic() {
        String data = FileUtils.readFileToString(ResourceUtils.getFile("classpath:data/face/data.topic.json"));
        return Result.success(JSON.parse(data));
    }

}
