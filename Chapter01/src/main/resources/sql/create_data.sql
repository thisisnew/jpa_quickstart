CREATE TABLE s_emp(
id		NUMBER(7)  	CONSTRAINT s_emp_id_nn NOT NULL, 	-- ���� ���̵�
name		VARCHAR2(25)   	CONSTRAINT s_emp_name_nn NOT NULL,	-- ���� �̸�
start_date	DATE,							-- �Ի���
title	VARCHAR2(25),						-- ����
dept_name	VARCHAR2(25),						-- �μ� �̸�
salary	NUMBER(11, 2),						-- �޿�
CONSTRAINT s_emp_id_pk PRIMARY KEY (id)
);

INSERT INTO s_emp VALUES (1, '������', '2002-12-03', '������ǥ�̻�', '������', 2500);
SELECT * FROM s_emp;
