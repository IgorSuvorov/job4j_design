create table students(
id serial primary key,
    name varchar(255),
	grade double precision,
	year int
);

insert into students(name, grade, year) values ('Maria', 4.23, 2022);
insert into students(name, grade, year) values ('Mike', 3.2, 2022);
insert into students(name, grade, year) values ('John', 3.3, 2022);

update students set name = 'Mary' where id = 3;

delete from students where id = 1;