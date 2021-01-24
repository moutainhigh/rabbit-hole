update ams_api set enabled = 1 where 1=1;
alter table ams_api modify enabled tinyint(1) default 1 not null comment '启用状态';
update ams_authority set enabled = 1 where 1=1;
alter table ams_authority modify enabled tinyint(1) default 1 not null comment '启用状态';
update ams_user_group set enabled = 1 where 1=1;
alter table ams_user_group modify enabled tinyint(1) default 1 not null comment '启用状态';
update ams_role set enabled = 1 where 1=1;
alter table ams_role modify enabled tinyint(1) default 1 not null comment '启用状态';
update com_comment set enabled = 1 where 1=1;
alter table com_comment modify enabled tinyint(1) default 1 not null comment '启用状态';
update com_comment_target set enabled = 1 where 1=1;
alter table com_comment_target modify enabled tinyint(1) default 1 not null comment '启用状态';
update com_data_dict set enabled = 1 where 1=1;
alter table com_data_dict modify enabled tinyint(1) default 1 not null comment '启用状态';
update com_data_dict_item set enabled = 1 where 1=1;
alter table com_data_dict_item modify enabled tinyint(1) default 1 not null comment '启用状态';
update com_district set enabled = 1 where 1=1;
alter table com_district modify enabled tinyint(1) default 1 not null comment '启用状态';
update com_short_url set enabled = 1 where 1=1;
alter table com_short_url modify enabled tinyint(1) default 1 not null comment '启用状态';
update mina_app_card set enabled = 1 where 1=1;
alter table mina_app_card modify enabled tinyint(1) default 1 not null comment '启用状态';
update mina_game_card set enabled = 1 where 1=1;
alter table mina_game_card modify enabled tinyint(1) default 1 not null comment '启用状态';

alter table com_district change ad_code adcode varchar(32) not null comment '区域编码';

# 排序
alter table com_data_dict_item change sort priority int default 1000 not null comment '排序';
alter table com_file change sort priority int default 1000 not null comment '排序';
alter table mina_app_card change sort priority int default 1000 not null comment '排序';
alter table mina_game_card change sort priority int default 1000 not null comment '排序';
alter table com_project change project_sn encoding varchar(32) not null comment '编码';
alter table ums_user change avatar avatar_url varchar(255) null comment '头像地址';



