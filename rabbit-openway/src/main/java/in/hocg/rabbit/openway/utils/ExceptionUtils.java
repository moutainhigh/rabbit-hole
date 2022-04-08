package in.hocg.rabbit.openway.utils;

import cn.hutool.core.util.StrUtil;
import com.alibaba.fastjson.JSONObject;
import in.hocg.boot.utils.struct.result.Result;
import lombok.experimental.UtilityClass;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;

/**
 * Created by hocgin on 2022/4/9
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class ExceptionUtils {

    public Mono<Void> handleException(ServerWebExchange exchange, Exception e) {
        Result<Void> result = Result.fail(StrUtil.nullToDefault(e.getMessage(), "未授权操作"));
        return Mono.defer(() -> Mono.just(exchange.getResponse())).flatMap((response) -> {
            response.setStatusCode(HttpStatus.OK);
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            DataBufferFactory dataBufferFactory = response.bufferFactory();
            DataBuffer buffer = dataBufferFactory.wrap(JSONObject.toJSONString(result).getBytes(Charset.defaultCharset()));
            return response.writeWith(Mono.just(buffer)).doOnError((error) -> DataBufferUtils.release(buffer));
        });
    }
}
