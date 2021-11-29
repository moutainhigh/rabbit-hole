package in.hocg.rabbit.mina.biz.pojo.ro;

import in.hocg.rabbit.mina.biz.pojo.dto.UploadYouTubeVideoDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by hocgin on 2021/10/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class UploadYouTubeVideoRo extends UploadYouTubeVideoDto {
    @ApiModelProperty("视频地址")
    private String videoUrl;
}
