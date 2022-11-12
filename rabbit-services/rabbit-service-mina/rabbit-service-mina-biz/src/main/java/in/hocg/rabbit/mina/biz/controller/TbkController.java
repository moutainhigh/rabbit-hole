package in.hocg.rabbit.mina.biz.controller;

import in.hocg.boot.utils.struct.result.Result;
import in.hocg.rabbit.mina.biz.pojo.ro.TbkBatchUrlRo;
import in.hocg.rabbit.mina.biz.service.TbkService;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author hocgin
 */
@Slf4j
@RestController
@Api(tags = "mina::淘宝客")
@RequestMapping("/tbk")
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class TbkController {
    private final TbkService service;

    @GetMapping("/asurl")
    public Result<String> asurl(@RequestParam("url") String url) {
        return Result.success(service.getPrivilegeLink(url));
    }

    @PostMapping("/batchAsUrl")
    public Result<Map<String, String>> batchAsUrl(@RequestBody TbkBatchUrlRo ro) {
        return Result.success(service.getBatchPrivilegeLink(ro));
    }

}
