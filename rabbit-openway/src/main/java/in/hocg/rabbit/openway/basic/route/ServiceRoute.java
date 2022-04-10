package in.hocg.rabbit.openway.basic.route;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Collections;
import java.util.List;

/**
 * Created by hocgin on 2022/4/8
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class ServiceRoute implements Serializable {
    private String serviceId;
    private Api api;

    public static class Api {

    }
}
