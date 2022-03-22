package in.hocg.rabbit.com.biz.manager.impl;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.StrUtil;
import in.hocg.rabbit.com.api.enums.CodeType;
import in.hocg.rabbit.com.biz.manager.UniqueCodeService;
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
public class UniqueCodeServiceImpl implements UniqueCodeService {
    private final Snowflake snowFlake;

    public String getUniqueCode(CodeType type) {
        final long code = snowFlake.nextId();
        return StrUtil.format("{}{}", type.getCodeStr(), code);
    }

}
