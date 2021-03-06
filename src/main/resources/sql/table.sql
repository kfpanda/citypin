create database park;

use park;;


drop table if exists park_info;
/*车位信息表*/
create table park_info(
    pno bigint AUTO_INCREMENT,
    createtime DECIMAL(13,0),
    updatetime DECIMAL(13,0),
    pname varchar(30),
    /*中继器编号*/
    recid varchar(10) not null,
    /*设备ID=$主机ID,信道,检测器ID*/
    devid varchar(10) not null,
    address varchar(100) not null,
    lat DECIMAL(10,6) not null,
    lng DECIMAL(10,6) not null,
    len double,
    wid double,
    price double,
    belong varchar(100),
    park int not null default 2,  /*0代表未停车，1代表停车，2代表无效*/
    ispub int not null default 0, /*1代表发布，0代表不发布*/
    pano bigint,
    primary key(pno)
);;
create unique index pi_pno_idx on park_info(pno);
create unique index pi_recid_devid_idx on park_info(recid, devid);
create index pi_lat_lng_idx on park_info(lat, lng);
create index pi_pano_idx on park_info(pano);


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
    hpic varchar(100),
    yt int default 1,
    level int not null default 1,
    levscore int not null default 0,
    score int not null default 0, /*1代表发布，0代表不发布*/
    status int(1) not null default 0,
    location varchar(40),
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

drop table if exists user_msg;
/*消息表*/
create table user_msg(
    mid bigint not null,
    account varchar(32) not null
);;
create unique index um_mid_account_idx on user_msg(mid, account);


/*** init ***/
insert into resource(name, pid, status, type, url) values('超级权限', 0, 1, 1, '/**');
insert into role(name, role) values('会员', 'ROLE_USER');
insert into role_res(rid, rno) values(1,1);


DROP TABLE IF EXISTS region;
/*区域表*/
CREATE TABLE region(
    rgno BIGINT AUTO_INCREMENT,
    province VARCHAR(32) NOT NULL,
    city VARCHAR(32) NOT NULL,
    towns VARCHAR(32) NOT NULL,
    PRIMARY KEY(rgno)
);;
CREATE UNIQUE INDEX r_rgno_idx ON region(rgno);


drop table if exists park_area;
/*车位区域表*/
create table park_area(
    pano bigint AUTO_INCREMENT,
    rgno bigint not null,
    createtime DECIMAL(13,0),
    updatetime DECIMAL(13,0),
    area varchar(128) not null,/*停车场名字*/
    addr varchar(128) not null, /*地址*/
    atype varchar(128) not null,/*停车场类型*/
    aimg varchar(128) not null,/*停车场实景图*/
    lng DECIMAL(10,6) not null,
    lat DECIMAL(10,6) not null,
    paytype DECIMAL(1,0) default 1 not null, /*收费方式*/
    price double,
    pnum int not null,/*总车位*/
    acolor varchar(5),
    priceday varchar(128),/*白天时段价格*/
    pricenight varchar(128),/*晚上时段价格*/
    opentime varchar(128), /*开放时间*/
    closetime varchar(128), /*关闭时间*/
    remark varchar(200),
    PRIMARY KEY(pano)
);;
create unique index pa_pano_idx on park_area(pano);


/*违章记录表*/
DROP TABLE IF EXISTS weizhang;

CREATE TABLE weizhang(
    wzid BIGINT AUTO_INCREMENT,
    createtime DECIMAL(13,0),
    updatetime DECIMAL(13,0),
    wztime varchar(20) NOT NULL,
    carno VARCHAR(12) NOT NULL,
    cjno VARCHAR(20) NOT NULL,
    area VARCHAR(70) NOT NULL,
    lng DECIMAL(10,6) not null,
    lat DECIMAL(10,6) not null,
    koufen DECIMAL(2,0) default 1 not null,
    fakuan VARCHAR(6) default '0' NOT NULL,
    wzdetail VARCHAR(200) NOT NULL,
    type DECIMAL(1,0) default 1 not null, 
    account varchar(32),
    PRIMARY KEY(wzid)
);;
CREATE UNIQUE INDEX tt_wzid_idx ON weizhang(wzid);
CREATE INDEX tt_lng_lat_idx ON weizhang(lng,lat);

/*贴条记录表*/
DROP TABLE IF EXISTS tietiao;

CREATE TABLE tietiao(
    ttid BIGINT AUTO_INCREMENT,
    createtime DECIMAL(13,0),
    updatetime DECIMAL(13,0),
    tttime varchar(20) NOT NULL,
    area VARCHAR(70) NOT NULL,
    lng DECIMAL(10,6) not null,
    lat DECIMAL(10,6) not null,
    wzdetail VARCHAR(200) NOT NULL,
    type DECIMAL(1,0) default 1 not null, 
    account varchar(32),
    PRIMARY KEY(ttid)
);;
CREATE UNIQUE INDEX tt_ttid_idx ON tietiao(ttid);
CREATE INDEX tt_lng_lat_idx ON tietiao(lng,lat);

/*吐槽记录表*/
DROP TABLE IF EXISTS laba;

CREATE TABLE laba(
    lbid BIGINT AUTO_INCREMENT,
    createtime DECIMAL(13,0),
    updatetime DECIMAL(13,0),
    type DECIMAL(1,0) not null,
    jyou DECIMAL(2,0) NOT NULL,
    jgtime DECIMAL(8,0),
    dccase varchar(100),
    tttime DECIMAL(13,0),
    location varchar(40) not null,
    tag varchar(20),
    img varchar(100),
    huati varchar(100),
    lng DECIMAL(10,6),
    lat DECIMAL(10,6),
    account varchar(32) not null,
    PRIMARY KEY(lbid)
);;
CREATE UNIQUE INDEX lb_lbid_idx ON laba(lbid);
CREATE INDEX lb_lng_lat_idx ON laba(lng,lat);
CREATE INDEX lb_account_idx ON laba(account);
CREATE INDEX laba_createtime_idx ON laba(createtime);


/*吐槽记录表*/
DROP TABLE IF EXISTS labacomment;

CREATE TABLE labacomment(
	lcid BIGINT AUTO_INCREMENT,
    lbid BIGINT not null,
    account varchar(32) not null,
    createtime DECIMAL(13,0),
    updatetime DECIMAL(13,0),
    content varchar(1000),
    lng DECIMAL(10,6),
    lat DECIMAL(10,6),
    PRIMARY KEY(lcid)
);;
CREATE UNIQUE INDEX lc_lcid_idx ON labacomment(lcid);
CREATE INDEX lc_lng_lat_idx ON labacomment(lng,lat);
CREATE INDEX lc_lbid_account_idx ON labacomment(lbid,account);


CREATE TABLE jianyi(
	jyid BIGINT AUTO_INCREMENT,
    account varchar(32) not null,
    createtime DECIMAL(13,0),
    content varchar(1000),
    PRIMARY KEY(jyid)
);;
CREATE UNIQUE INDEX jy_jyid_idx ON jianyi(jyid);
