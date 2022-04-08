package in.hocg.rabbit.mina.biz.pojo.dto;

import cn.hutool.crypto.digest.MD5;
import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2022/4/7
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */

@Data
@Accessors(chain = true)
public class Notify<T> {
    @ApiModelProperty("消息体")
    private T body;
    @ApiModelProperty("签名")
    private String sign;


    public static Notify create(Object body) {
        Notify<Object> result = new Notify<>();
        result.setBody(body);
        result.setSign(MD5.create().digestHex(JSON.toJSONString(body)));
        return result;
    }
}
