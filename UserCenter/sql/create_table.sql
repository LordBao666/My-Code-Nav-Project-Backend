create table code_nav_user_center.user
(
    id            bigint auto_increment
        primary key,
    username      varchar(256)                       null comment '用户昵称',
    user_account  varchar(256)                       null comment '登录账号',
    avatar_url    varchar(1024)                      null comment '用户头像',
    gender        tinyint                            null comment '性别',
    user_password varchar(256)                       not null comment '用户密码',
    phone         varchar(256)                       null comment '手机号',
    email         varchar(256)                       null comment '邮箱',
    user_status   int                                null comment '用户状态',
    create_time   datetime default (now())           null comment '创建时间',
    update_time   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP comment '更新时间',
    is_delete     tinyint  default 0                 null comment '逻辑删除位',
    user_role     int      default 0                 not null comment '用户身份 0表示普通用户 1表示管理员',
    planet_code   varchar(512)                       null comment '星球编号',
    tags          varchar(1024)                      null comment '用户标签'
)
    comment '用户信息';

