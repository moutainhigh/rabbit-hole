package in.hocg.rabbit.mina.biz.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by hocgin on 2022/3/31
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
public class Y2bChannelCompleteVo {
    @ApiModelProperty
    private Long id;
    @ApiModelProperty("授权服务")
    private String clientId;
    @ApiModelProperty("频道ID")
    private String channelId;
    @ApiModelProperty("频道名称")
    private String title;
    @ApiModelProperty("频道地址")
    private String url;
    @ApiModelProperty("频道图片")
    private String imageUrl;
}
