package in.hocg.rabbit.rcm.biz.pojo.ro;

import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.ro.CompleteRo;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2022/4/17
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class PostCategoryCompleteRo extends CompleteRo {
    private String keyword;
}
