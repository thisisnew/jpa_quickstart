create table s_emp(
	id number(7) constraint s_emp_id_nn not null,
	name varchar2(25) constraint s_emp_name_nn not null,
	start_date date,
	title varchar2(25),
	dept_name varchar2(25),
	salary number(11,2),
	constraint s_emp_id_pk primary key (id)
);

insert into s_emp values(1,'안은경','2002-12-03', '영업대표이사', '영업부', 2500);

select * from s_emp;