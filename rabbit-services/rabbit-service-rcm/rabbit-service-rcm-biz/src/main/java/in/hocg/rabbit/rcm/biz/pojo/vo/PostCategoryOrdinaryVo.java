package in.hocg.rabbit.rcm.biz.pojo.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2022/4/16
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class PostCategoryOrdinaryVo {
    private Long id;
    private String encoding;
    private String title;
}
