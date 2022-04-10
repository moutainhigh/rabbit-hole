package in.hocg.rabbit.openway.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONObject;
import cn.hutool.json.JSONUtil;
import in.hocg.boot.utils.struct.result.Result;
import in.hocg.rabbit.openway.constants.OpenwayContants;
import lombok.experimental.UtilityClass;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.core.io.buffer.DataBufferFactory;
import org.springframework.core.io.buffer.DataBufferUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by hocgin on 2022/4/9
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class ExceptionUtils {

    public Mono<Void> handleException(ServerWebExchange exchange, String method, Exception e) {
        return Mono.defer(() -> Mono.just(exchange.getResponse())).flatMap((response) -> {
            response.setStatusCode(HttpStatus.OK);
            response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
            DataBufferFactory dataBufferFactory = response.bufferFactory();
            DataBuffer buffer = dataBufferFactory.wrap(JSONUtil.toJsonStr(result(method, Result.fail(StrUtil.nullToDefault(e.getMessage(), "未授权操作")))).getBytes(Charset.defaultCharset()));
            return response.writeWith(Mono.just(buffer)).doOnError((error) -> DataBufferUtils.release(buffer));
        });
    }

    public Map<String, Object> result(String method, Result<Object> finalBody) {
        return result(method, JSONUtil.toJsonStr(finalBody));
    }

    public Map<String, Object> result(String method, String finalBody) {
        return new HashMap<>(2) {{
            put(OpenwayContants.SIGN, OpenwayUtils.getSignStr(finalBody));
            put(method, JSONUtil.toBean(finalBody, JSONObject.class));
        }};
    }
}
