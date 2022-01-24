package in.hocg.rabbit.ws.api;

import in.hocg.rabbit.common.constant.GlobalConstant;
import lombok.experimental.UtilityClass;

/**
 * Created by hocgin on 2020/8/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@UtilityClass
public class WsServiceName {
    public final String NAME = GlobalConstant.SERVICE_NAME;
    public final String PACKAGE = "in.hocg.rabbit.ws";
    public final String FEIGN_HEADER = "X-Feign=Y";
}
