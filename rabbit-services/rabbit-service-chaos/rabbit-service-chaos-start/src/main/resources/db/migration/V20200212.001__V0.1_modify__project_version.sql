alter table com_project
    add version varchar(32) default 'v1.0.0' not null comment '当前版本号';

