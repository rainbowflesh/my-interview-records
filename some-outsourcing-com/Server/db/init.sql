-- # 统一信息
-- #     id          varchar(255) not null primary key ,
-- #     remark      varchar(255) comment 'mark',
-- #     created_at  timestamp    not null default current_timestamp comment 'created time',
-- #     modifier    varchar(255)          default 'direct' comment 'last modifier id',
-- #     modified_at timestamp             default current_timestamp comment 'modified time',
-- #     is_delete   boolean               default '0' null comment 'logical delete'
-- #
drop table account;
create table if not exists account
(
    id          varchar(255) not null primary key,
    username    varchar(255) not null unique,
    email       varchar(255) not null unique,
    avatar      varchar(255) not null ,
    password    varchar(255) not null,
    status      int          null,
    last_login  timestamp    null,
    remark      varchar(255) null,
    created     timestamp    not null default current_timestamp,
    modifier    varchar(255)          default 'Automatically generated',
    modified_at timestamp             default current_timestamp,
    is_delete   boolean               default '0' null
);