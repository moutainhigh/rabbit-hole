alter table mina_app_card
    add is_top tinyint(1) default 0 not null comment '是否置顶';
