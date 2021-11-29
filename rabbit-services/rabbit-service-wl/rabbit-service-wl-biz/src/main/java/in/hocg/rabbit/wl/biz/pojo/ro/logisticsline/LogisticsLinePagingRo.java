package in.hocg.rabbit.wl.biz.pojo.ro.logisticsline;

import in.hocg.boot.mybatis.plus.autoconfiguration.ro.PageRo;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.Collections;
import java.util.List;

/**
 * Created by hocgin on 2020/11/12
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class LogisticsLinePagingRo extends PageRo {
    private Long companyId;
    private List<Long> warehouseId = Collections.emptyList();
    @ApiModelProperty("[终点]省区域编码")
    private String provinceAdcode;
    @ApiModelProperty("[终点]市区区域编码")
    private String cityAdcode;
}
