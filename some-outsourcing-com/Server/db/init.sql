-- # 统一信息
-- #     id          varchar(255) not null primary key ,
-- #     remark      varchar(255) comment 'mark',
-- #     created_at  timestamp    not null default current_timestamp comment 'created time',
-- #     modifier    varchar(255)          default 'direct' comment 'last modifier id',
-- #     modified_at timestamp             default current_timestamp comment 'modified time',
-- #     is_delete   boolean               default '0' null comment 'logical delete'
-- #
drop table account;
create table account
(
    id          serial       not null primary key,
    username    varchar(255) not null unique,
    email       varchar(255) not null unique,
    avatar      varchar(255) not null ,
    password    varchar(255) not null,
    status      int          default 1 null,
    last_login  timestamp    null,
    remark      varchar(255) null,
    created     timestamp    not null default current_timestamp,
    modifier    varchar(255)          default 'Automatically generated',
    modified_at timestamp             default current_timestamp,
    is_delete   boolean               default '0' null
);
drop table blogs;
create table blogs
(
    id          serial       not null primary key,
    user_id     varchar(255) not null,
    title       varchar(255) not null unique,
    description varchar(255) null ,
    content     text         null ,
    status      int          default 1  null,
    votes       int          null,
    last_login  timestamp    null,
    remark      varchar(255) null,
    created     timestamp    not null default current_timestamp,
    modifier    varchar(255)          default 'Automatically generated',
    modified_at timestamp             default current_timestamp,
    is_delete   boolean               default '0' null
);
insert into account (
    avatar,
    email,
    "password",
    username
    )
values (
    'test',
    'test@mail.com',
    '098f6bcd4621d373cade4e832627b4f6',
    'test'
    )
;
insert into blogs (
    "content",
    description,
    title,
    user_id,
    votes)
values (
    '111',
    '111:content',
    '1',
    '1',
    1)
;insert into blogs (
    "content",
    description,
    title,
    user_id,
    votes)
values (
    '222',
    '222:content',
    '2',
    '2',
    1)
;
