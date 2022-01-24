package in.hocg.rabbit.ums.biz.pojo.ro;

import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.ro.CompleteRo;
import lombok.Data;

/**
 * Created by hocgin on 2021/1/23
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class UserCompleteRo extends CompleteRo {
    private String keyword;

}
