drop table park_info;
/*车位信息表*/
create table park_info(
    pid bigint AUTO_INCREMENT,
    createtime DECIMAL(13,0),
    updatetime DECIMAL(13,0),
    pname varchar(30),
    recid varchar(10) not null,
    devid varchar(10) not null,
    address varchar(100) not null,
    lat DECIMAL(10,6) not null,
    lng DECIMAL(10,6) not null,
    ispub int not null default 0, /*1代表发布，0代表不发布*/
    primary key(pid)
);
create unique index pi_pid_idx on park_info(pid);
create unique index pi_recid_devid_idx on park_info(recid, devid);
create index pi_lat_lng_idx on park_info(lat, lng);