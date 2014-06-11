drop table if exists park_info;
/*车位信息表*/
create table park_info(
    pno bigint AUTO_INCREMENT,
    createtime DECIMAL(13,0),
    updatetime DECIMAL(13,0),
    pname varchar(30),
    recid varchar(10) not null,
    devid varchar(10) not null,
    address varchar(100) not null,
    lat DECIMAL(10,6) not null,
    lng DECIMAL(10,6) not null,
    len double,
    wid double,
    price double,
    belong varchar(100),
    ispub int not null default 0, /*1代表发布，0代表不发布*/
    primary key(pno)
);;
create unique index pi_pno_idx on park_info(pno);
create unique index pi_recid_devid_idx on park_info(recid, devid);
create index pi_lat_lng_idx on park_info(lat, lng);


drop table if exists users;
/*用户信息表*/
create table users(
    account varchar(32) not null,
    createtime DECIMAL(13,0),
    updatetime DECIMAL(13,0),
    passwd varchar(64) not null,
    phone varchar(11) not null,
    nkname varchar(30) not null,
    uname varchar(30),
    level int not null default 1,
    levscore int not null default 0,
    score int not null default 0, /*1代表发布，0代表不发布*/
    status int(1) not null default 0,
    address varchar(100),
    vehtype varchar(30),
    remark varchar(1000),
    primary key(account)
);;
create unique index ui_account_idx on users(account);

drop table if exists role;
/*角色信息表*/
create table role(
	rid bigint AUTO_INCREMENT,
    name varchar(32) not null,
    role varchar(32) not null,
    primary key(rid)
);;
create unique index r_role_idx on role(role);

drop table if exists user_role;
/*角色信息表*/
create table user_role(
    account varchar(32) not null,
    rid bigint not null
);;
create unique index ur_account_rid_idx on user_role(account, rid);

drop table if exists resource;
/*角色信息表*/
create table resource(
    rno bigint AUTO_INCREMENT,
    name varchar(32) not null,
    pid bigint,
    status DECIMAL(1,0) not null default 1,
    type DECIMAL(1,0) not null default 1,
    url varchar(200),
    primary key(rno)
);;
create unique index rs_rno_idx on resource(rno);

drop table if exists role_res;
create table role_res(
    rid bigint not null,
    rno bigint not null
);;
create unique index rr_rid_rno_idx on role_res(rid, rno);


drop table if exists order_info;
/*订单信息表*/
create table order_info(
    ono bigint AUTO_INCREMENT,
    createtime DECIMAL(13,0),
    updatetime DECIMAL(13,0),
    account varchar(32) not null,
    pno bigint not null,
    stime DECIMAL(13,0) not null,
    etime DECIMAL(13,0) not null,
    price double not null,
    cost double not null,
    primary key(ono)
);;
create unique index oi_ono_idx on order_info(ono);
create index oi_account_pno_idx on order_info(account, pno);
create index oi_account_idx on order_info(account);


drop table if exists message;
/*消息表*/
create table message(
    mid bigint AUTO_INCREMENT,
    createtime DECIMAL(13,0),
    updatetime DECIMAL(13,0),
    title varchar(100) not null,
    frm  varchar(30) not null,
    intro  varchar(200),
    content varchar(3000) not null,
    primary key(mid)
);;
create unique index msg_mid_idx on message(mid);

