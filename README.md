## 仿码匠社区的一个demo

## 工具
git，idea。。。


## 资料
spring，。。

## 命令
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate

## 数据库
```mysql
create table user
(
    id int auto_increment primary key not null,
    account_id varchar(100),
    name varchar(50),
    token varchar(36),
    gmt_create bigint,
    gmt_modified bigint,
    avatar_url varchar(128)
);
```
```mysql
create table question
(
    id int auto_increment primary key,
    title varchar(50),
    description text,
    gmt_create bigint,
    gmt_modified bigint,
    creator int,
    comment_count int default 0,
    view_count int default 0,
    like_count int default 0,
    tag varchar(256)
);
```
```mysql
create table comment
(
    id bigint auto_increment primary key,
    parent_id bigint not null,
    type int not null,
    commentator int not null,
    gmt_create bigint not null,
    gmt_modified bigint not null,
    like_count bigint default 0
);
```