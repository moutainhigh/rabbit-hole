package in.hocg.rabbit.mina.biz.pojo.ro;

import com.google.common.collect.Lists;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * Created by hocgin on 2022/4/3
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class UploadCollectionRo {
    @ApiModelProperty("抖音集合短链接地址")
    private String videoUrl;
    @ApiModelProperty("追加tag")
    private List<String> addTags = Lists.newArrayList("动漫", "Animation");
}
