package in.hocg.rabbit.openway.basic.data;

import lombok.Data;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * Created by hocgin on 2022/4/9
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class RequestBody implements Serializable {
    /**
     * 请求方法
     */
    private String method;
    /**
     * 应用编号
     */
    private String appid;
    /**
     * 签名类型
     */
    private String signType;
    /**
     * 签名
     */
    private String sign;
    /**
     * 时间戳
     */
    private LocalDateTime timestamp;
    /**
     * 请求内容
     */
    private String bizContent;


    @Getter
    @RequiredArgsConstructor
    public enum SignType {
        MD5("md5", "MD5");
        private final String type;
        private final String name;
    }
}
