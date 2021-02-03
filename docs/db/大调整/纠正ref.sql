alter table com_file change rel_id ref_id bigint not null comment '业务ID';
alter table com_file change rel_type ref_type varchar(10) default 'unknown' not null comment '业务类型';

alter table com_comment_target change rel_id ref_id bigint not null comment '评论对象ID';
alter table com_comment_target change rel_type ref_type varchar(32) not null comment '评论对象类型';

