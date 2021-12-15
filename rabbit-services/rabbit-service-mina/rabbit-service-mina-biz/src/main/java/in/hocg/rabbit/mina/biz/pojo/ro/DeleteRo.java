package in.hocg.rabbit.mina.biz.pojo.ro;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;

/**
 * Created by hocgin on 2021/12/1
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
public class DeleteRo implements Serializable {
    @Size(min = 1, message = "id 参数错误")
    @NotNull(message = "id 参数错误")
    private List<Long> id;
}
