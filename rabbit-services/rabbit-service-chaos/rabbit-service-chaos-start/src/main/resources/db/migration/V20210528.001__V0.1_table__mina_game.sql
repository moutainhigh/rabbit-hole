alter table mina_game_card
    add bodyStr text null comment '简介';

alter table mina_game_card
    add score decimal(10, 2) default 10 not null comment '评分';

alter table mina_game_card
    add heat bigint(10) default 0 not null comment '热度';

alter table mina_game_card
    add game_type varchar(32) default 'unknown' not null comment '游戏类型';

