package in.hocg.rabbit.com.biz.pojo.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Created by hocgin on 2022/2/13
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class DocTextVo {
    @ApiModelProperty("文本")
    private String text;
    @ApiModelProperty("关键词")
    private List<String> keyword;
}
