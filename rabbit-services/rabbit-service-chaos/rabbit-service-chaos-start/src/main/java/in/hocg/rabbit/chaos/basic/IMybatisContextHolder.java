package in.hocg.rabbit.chaos.basic;

import in.hocg.boot.mybatis.plus.autoconfiguration.core.context.MybatisContextHolder;
import in.hocg.rabbit.usercontext.autoconfigure.UserContextHolder;
import org.springframework.stereotype.Component;

/**
 * Created by hocgin on 2022/1/3
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Component
public class IMybatisContextHolder implements MybatisContextHolder {
    @Override
    public Long getUserId() {
        return UserContextHolder.getUserId().orElse(null);
    }

    @Override
    public Long getTenantId() {
        return MybatisContextHolder.super.getTenantId();
    }
}
