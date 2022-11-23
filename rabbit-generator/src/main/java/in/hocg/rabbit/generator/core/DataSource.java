package in.hocg.rabbit.generator.core;

import com.baomidou.mybatisplus.annotation.DbType;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.sql.Driver;

/**
 * Created by hocgin on 2020/5/29.
 * email: hocgin@gmail.com
 *
 * @author hocgin
 */
@Getter
@RequiredArgsConstructor
public enum DataSource {
    DEFAULT(DbType.MYSQL, "db_url", com.mysql.cj.jdbc.Driver.class, "username", "password");
    private final DbType dbType;
    private final String url;
    private final Class<? extends Driver> driverName;
    private final String username;
    private final String password;
}
