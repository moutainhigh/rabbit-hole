package in.hocg.rabbit.openway.basic.data;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Created by hocgin on 2022/4/8
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class ServiceRoute implements Serializable {
    private String apiId;
    private String mapTargetUri;
    private String mapTargetPath;
}
