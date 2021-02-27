-- auto-generated definition
create table goods
(
    id          bigint unsigned auto_increment comment '主键'
        primary key,
    code        varchar(128)    not null comment '商品编码',
    name        varchar(128)    not null comment '商品名称',
    price       bigint unsigned not null comment '商品价格',
    create_time timestamp       not null,
    update_time timestamp       not null
)
    comment '商品信息';

-- auto-generated definition
create table user
(
    id          bigint unsigned auto_increment comment '主键'
        primary key,
    user_id     varchar(128) not null comment '用户ID',
    user_name   varchar(128) not null comment '用户名',
    password    varchar(128) not null comment '密码',
    nick_name   varchar(128) not null comment '昵称',
    card_number varchar(18)  not null comment '身份证号',
    create_time timestamp    not null,
    update_time timestamp    not null,
    constraint uk_user_id
        unique (user_id)
)
    comment '用户信息';

-- auto-generated definition
create table order_details
(
    id          bigint unsigned auto_increment comment '主键'
        primary key,
    order_id    bigint       not null comment '订单ID',
    good_code   varchar(128) not null comment '商品编码',
    good_name   varchar(128) not null comment '商品名称',
    quantity    int unsigned not null comment '商品数量',
    create_time timestamp    not null,
    update_time timestamp    not null
)
    comment '订单明细表';

create index idx_order_id
    on order_details (order_id);

-- auto-generated definition
create table `order`
(
    id          bigint unsigned auto_increment comment '主键'
        primary key,
    user_id     varchar(128)     not null comment '用户id',
    amount      bigint unsigned  not null comment '金额(分)',
    status      tinyint unsigned not null comment '状态',
    pay_time    timestamp        null comment '支付时间',
    create_time timestamp        not null,
    update_time timestamp        not null
)
    comment '订单信息';

学习笔记