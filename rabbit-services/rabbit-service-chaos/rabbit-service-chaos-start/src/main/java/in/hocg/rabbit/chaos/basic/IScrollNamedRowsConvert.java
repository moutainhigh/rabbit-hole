package in.hocg.rabbit.chaos.basic;

import com.baomidou.mybatisplus.core.metadata.IPage;
import in.hocg.boot.mybatis.plus.autoconfiguration.core.pojo.vo.IScroll;
import in.hocg.boot.named.autoconfiguration.core.convert.NamedRowsConvert;
import org.springframework.stereotype.Component;

/**
 * Created by hocgin on 2022/4/17
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
public class IScrollNamedRowsConvert implements NamedRowsConvert {
    @Override
    public boolean isMatch(Class<?> aClass) {
        return IScroll.class.isAssignableFrom(aClass);
    }

    @Override
    public Object convert(Object o) {
        return ((IScroll) o).getRecords();
    }
}
