create database if not exists hibernate_01;
use hibernate_01
drop table if exists user;
create table if not exists user(
     id int primary key auto_increment,
     username varchar(20)
); 