package in.hocg.rabbit.mina.biz.pojo.ro;

import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.ro.PageRo;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * Created by hocgin on 2021/3/13
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@ApiModel
public class MinaMobileWallpaperTagsPagingRo extends PageRo {
    private String keyword;
}
