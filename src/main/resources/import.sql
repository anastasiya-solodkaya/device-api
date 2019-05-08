insert into brand(name) values('samsung');
insert into brand(name) values('motorolla');
insert into brand(name) values('lg');
insert into brand(name) values('huawei');
insert into brand(name) values('apple');
insert into brand(name) values('nokia');

insert into model(brand_id, name, descriptive_name) select id, 's7', 'Samsung Galaxy S7' from brand where name='samsung';
insert into model(brand_id, name, descriptive_name) select id, 's8', 'Samsung Galaxy S8' from brand where name='samsung';
insert into model(brand_id, name, descriptive_name) select id, 's9', 'Samsung Galaxy S9' from brand where name='samsung';
insert into model(brand_id, name, descriptive_name) select id, 'nexus 6', 'Motorolla Nexus 6' from brand where name='motorolla';
insert into model(brand_id, name, descriptive_name) select id, 'nexus 5x', 'LG Nexus 5X' from brand where name='lg';
insert into model(brand_id, name, descriptive_name) select id, 'honor 7x', 'Hawei Honor 7X' from brand where name='huawei';
insert into model(brand_id, name, descriptive_name) select id, 'iphone X', 'Apple iPhone X' from brand where name='apple';
insert into model(brand_id, name, descriptive_name) select id, 'iphone 8', 'Apple iPhone 8' from brand where name='apple';
insert into model(brand_id, name, descriptive_name) select id, 'iphone 4s', 'Apple iPhone 4s' from brand where name='apple';
insert into model(brand_id, name, descriptive_name) select id, '3310', 'Nokia 3310' from brand where name='nokia';

insert into available_device(model_id, device_comment) select id, 'default device' from model;

