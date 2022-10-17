package in.hocg.rabbit.mina.biz.pojo.ro;

import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.ro.PageRo;
import io.swagger.annotations.ApiModel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

/**
 * Created by hocgin on 2021/1/1
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@Setter
@ApiModel(description = "游戏")
@EqualsAndHashCode(callSuper = true)
public class MinaGameCardPagingRo extends PageRo {
    private String gameType;
    private String keyword;
    private String appid;
    private Serializable enabled;
}
