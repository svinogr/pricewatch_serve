create table host (id int8 generated by default as identity, name varchar(255), primary key (id));
create table items (id int8 generated by default as identity, img_link varchar(255), title varchar(255), url_link varchar(255), primary key (id));
create table prices (id int8 generated by default as identity, date int8, price float8, item_id int8 not null, primary key (id));
alter table if exists items drop constraint if exists UK_pbuxytj6le5cvm5v3q5cn337d;
alter table if exists items add constraint UK_pbuxytj6le5cvm5v3q5cn337d unique (url_link);
alter table if exists prices add constraint FKp1r8j8s3x73y1da5r8r9tme9t foreign key (item_id) references items;

