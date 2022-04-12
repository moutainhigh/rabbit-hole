package in.hocg.rabbit.openway.basic.context;

import cn.hutool.json.JSONUtil;
import in.hocg.rabbit.openway.basic.data.RequestBody;
import lombok.Data;
import lombok.experimental.Accessors;
import org.springframework.http.codec.multipart.Part;
import org.springframework.util.MultiValueMap;

import java.io.Serializable;

/**
 * Created by hocgin on 2022/4/9
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class GatewayContext implements Serializable {
    public static final String NAME = "GatewayContext";
    /**
     * 请求路径
     */
    private String path;
    /**
     * 请求体
     */
    private String body;
    /**
     * form 表单
     */
    private MultiValueMap<String, Part> form;

    public RequestBody getRequestBody() {
        return JSONUtil.toBean(body, RequestBody.class);
    }
}
