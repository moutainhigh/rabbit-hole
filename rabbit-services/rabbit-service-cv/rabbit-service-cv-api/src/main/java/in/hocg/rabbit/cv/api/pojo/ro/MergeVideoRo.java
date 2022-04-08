package in.hocg.rabbit.cv.api.pojo.ro;

import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Created by hocgin on 2022/4/4
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class MergeVideoRo {
    private List<String> files;
    private String output;
}
