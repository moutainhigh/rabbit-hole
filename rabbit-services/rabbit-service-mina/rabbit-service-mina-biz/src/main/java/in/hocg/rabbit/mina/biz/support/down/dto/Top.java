package in.hocg.rabbit.mina.biz.support.down.dto;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

/**
 * Created by hocgin on 2022/4/4
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Data
@Accessors(chain = true)
public class Top<V> {
    private Long hotValue;
    private V value;
}
