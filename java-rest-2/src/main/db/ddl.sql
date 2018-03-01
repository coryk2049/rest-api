create database poc_subscriber;
show databases;
use poc_subscriber;
show tables;
create table bl_subscriber (
    id int not null primary key AUTO_INCREMENT,
    name varchar(80) not null,
    phone varchar(80) not null,
    email varchar(80) not null
);
show tables;
describe bl_subscriber;
insert into bl_subscriber(id,name,phone,email) values (null, "Tony Stark", "5716436000","tony.stark@example.com");
commit;
select id,name,phone,email from bl_subscriber;
