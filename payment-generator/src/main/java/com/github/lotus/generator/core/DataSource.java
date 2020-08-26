package com.github.lotus.generator.core;

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
    DEFAULT(DbType.MYSQL,
        "jdbc:mysql://mysql.lotus.github.com:13306/db_test?useSSL=false&useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&allowPublicKeyRetrieval=true",
        com.mysql.cj.jdbc.Driver.class, "root", "hocgin");
    private final DbType dbType;
    private final String url;
    private final Class<? extends Driver> driverName;
    private final String username;
    private final String password;
}
