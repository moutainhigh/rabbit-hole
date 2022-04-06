package in.hocg.rabbit.mina.biz.docking.recharge.pojo.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * Created by hocgin on 2022/4/6
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class ResultVo<T> {
    private String errno;
    private String errmsg;
    private T data;

    public boolean isSuccess() {
        return "0".equals(errno);
    }
}
