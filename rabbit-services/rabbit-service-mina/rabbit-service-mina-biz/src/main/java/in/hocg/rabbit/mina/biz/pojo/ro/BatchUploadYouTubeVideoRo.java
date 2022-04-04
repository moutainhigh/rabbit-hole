package in.hocg.rabbit.mina.biz.pojo.ro;

import in.hocg.rabbit.mina.biz.pojo.dto.UploadY2bDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by hocgin on 2021/10/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class BatchUploadYouTubeVideoRo extends UploadY2bDto {
    @ApiModelProperty("本地目录")
    private String localDir = "/Users/hocgin/Downloads/动漫视频/一念永恒(第一季)";
}
