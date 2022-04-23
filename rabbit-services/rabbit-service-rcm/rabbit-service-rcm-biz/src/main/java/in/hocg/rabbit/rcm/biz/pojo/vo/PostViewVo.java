package in.hocg.rabbit.rcm.biz.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Created by hocgin on 2022/4/23
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
@Accessors(chain = true)
public class PostViewVo implements Serializable {
    @ApiModelProperty("标题")
    private String title;
    private PublishedDocVo doc;
}
