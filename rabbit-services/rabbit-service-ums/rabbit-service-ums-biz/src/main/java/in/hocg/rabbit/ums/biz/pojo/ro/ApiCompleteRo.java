package in.hocg.rabbit.ums.biz.pojo.ro;

import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.ro.CompleteRo;
import lombok.Data;

/**
 * Created by hocgin on 2021/1/24
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class ApiCompleteRo extends CompleteRo {
    private String keyword;
}
