package in.hocg.rabbit.chaos.biz.pojo.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Getter
@Setter
@Accessors(chain = true)
public class VerifyCodeDto implements Serializable {
    private String optType;
    private String verifyCode;
    private String deviceType;
    private String toDevice;
}
