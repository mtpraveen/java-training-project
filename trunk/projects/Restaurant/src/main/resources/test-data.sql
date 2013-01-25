insert into section (name_section) values ('Salads');
insert into section (name_section) values ('First dishes');
insert into section (name_section) values ('Second dishes');
insert into section (name_section) values ('Drinks');

insert into dish values ('Caesar','Salads',15, 'bread, cheese', 'It is vere tasty',null);
insert into dish values ('Neptune','Salads',12, 'crab sticks', 'It is vere tasty',null);
insert into dish values ('Draniki','Second dishes',20, 'potatoes', 'It is a national dish',null);
insert into dish values ('Tea','Drinks', 5, 'black tea', 'From India', null);
insert into dish values ('Coffee','Drinks', 5, 'Ground', 'From Brazil', null);

insert into ROLE values ('admin', null);
insert into ROLE values ('user', null);

insert into app_user values ('admin', 'e10adc3949ba59abbe56e057f20f883e', 'Nikolay', 'Kisel', '1989-12-19', 251249, 'Brest', '12-23');
insert into app_user values ('user', 'e10adc3949ba59abbe56e057f20f883e', 'Ivan', 'Ivanov', '1990-07-10', 234513, 'Minsk', '12-23');

insert into user_has_role values ('admin', 'admin');
insert into user_has_role values ('admin', 'user');
insert into user_has_role values ('user', 'user');

insert into user_order values (null, 'user', 'Brest', '12-34', '2012-12-06', 10, 100, 1, 0);
insert into user_order values (null, 'user', 'Brest', '12-234', '2012-12-10', 15, 120, 1, 1);

insert into order_has_dish values (1, 'Neptune');
insert into order_has_dish values (1, 'Coffee');
insert into order_has_dish values (2, 'Caesar');