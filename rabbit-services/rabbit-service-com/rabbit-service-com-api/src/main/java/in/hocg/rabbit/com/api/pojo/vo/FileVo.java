package in.hocg.rabbit.com.api.pojo.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * Created by hocgin on 2020/2/19.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
@Accessors(chain = true)
public class FileVo implements Serializable {
    @ApiModelProperty("地址")
    private String url;
    @ApiModelProperty("文件名")
    private String filename;
}
