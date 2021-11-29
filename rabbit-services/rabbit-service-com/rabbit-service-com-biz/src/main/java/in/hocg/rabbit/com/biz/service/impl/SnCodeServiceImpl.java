package in.hocg.rabbit.com.biz.service.impl;

import cn.hutool.core.lang.Snowflake;
import in.hocg.rabbit.com.biz.service.SnCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * Created by hocgin on 2020/3/15.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
@RequiredArgsConstructor(onConstructor = @__(@Lazy))
public class SnCodeServiceImpl implements SnCodeService {
    private final Snowflake snowFlake;

    public String getSnCode(String type) {
        final long code = snowFlake.nextId();
        return type + code;
    }

}
