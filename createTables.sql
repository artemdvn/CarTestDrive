DROP DATABASE IF EXISTS cartestdrive;

CREATE DATABASE cartestdrive DEFAULT CHARACTER SET 'utf8';

USE cartestdrive;

create table showrooms
(
  showroom_id int unsigned not null auto_increment,
  nameShowroom varchar(255) not null,
  address varchar(255) not null,
  primary key (showroom_id)
) engine=InnoDB;

create table cars
(
  car_id int unsigned not null auto_increment,
  carName varchar(255) not null,
  modelName varchar(255) not null,
  yearOfIssue int not null,
  showroom_id int not null,
  mileage int not null,
  reserved boolean not null,
  primary key (car_id)
) engine=InnoDB;

set names 'utf8';

insert into showrooms (nameShowroom, address) 
values ('Nissan Center', 'Zaporizke shosse str. 22');
insert into showrooms (nameShowroom, address) 
values ('Laguna', 'Dnipropetrivsk, Slobozhanski avn. 127');

insert into cars (carName, modelName, yearOfIssue, showroom_id, mileage, reserved)
values ('Nissan', 'Note', 2013, 1, 7500, false);

insert into cars (carName, modelName, yearOfIssue, showroom_id, mileage, reserved)
values ('Nissan', 'X-Trail', 2015, 1, 8800, false);

insert into cars (carName, modelName, yearOfIssue, showroom_id, mileage, reserved)
values ('Nissan', 'GT-R', 2016, 1, 200, false);

insert into cars (carName, modelName, yearOfIssue, showroom_id, mileage, reserved)
values ('Renault', 'Megane', 2014, 2, 11100, false);

insert into cars (carName, modelName, yearOfIssue, showroom_id, mileage, reserved)
values ('Renault', 'Logan', 2016, 2, 500, false);

insert into cars (carName, modelName, yearOfIssue, showroom_id, mileage, reserved)
values ('Renault', 'Laguna', 2013, 2, 12500, false);
