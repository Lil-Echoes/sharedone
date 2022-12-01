drop table employee;
--직원 테이블
CREATE TABLE employee (
	EMPCD	varchar(20)	NOT NULL PRIMARY KEY,
	PW	varchar(40)	NOT NULL,
	PHONE_NO	VARCHAR(20)	NOT NULL,
    name varchar(20) NOT NULL,
	EMAIL	VARCHAR(50)	NOT NULL,
	HIREDATE	date	NOT NULL,
    DEPT VARCHAR(20) NOT NULL,
	JOB	VARCHAR(10)	NOT NULL
);
-- 판매부번 테이블
CREATE TABLE product (
	productcd VARCHAR2(20) PRIMARY KEY,
	productnm VARCHAR2(50),
	unit VARCHAR2(10),
	productgroup VARCHAR2(20)
);
CREATE TABLE NOTICE (
	NOTICECD	number(11)	NOT NULL,
	CONTENT	VARCHAR(1000)	NOT NULL,
	EMPCD	varchar(20)	NOT NULL  references m_employee(empcd),
	SONO	VARCHAR2(20)	NOT NULL  references order(SONO),
	registDate	date	NOT NULL
);
SELECT * FROM product;

INSERT INTO product VALUES ( 'A00001', '새우깡', 'box', '스넥류' );
INSERT INTO product VALUES ( 'A00002', '포카칩', 'box', '스넥류' );
INSERT INTO product VALUES ( 'A00003', '바나나킥', 'box', '스넥류' );
INSERT INTO product VALUES ( 'A00004', '꼬북칩', 'box', '스넥류' );
INSERT INTO product VALUES ( 'A00005', '빈츠', 'box', '스넥류' );
INSERT INTO product VALUES ( 'B00001', 'ABC 초콜릿', 'box', '초콜릿류' );
INSERT INTO product VALUES ( 'B00002', '트윅스', 'box', '초콜릿류' );
INSERT INTO product VALUES ( 'B00003', '스니커즈', 'box', '초콜릿류' );
INSERT INTO product VALUES ( 'B00004', '카카오 72', 'box', '초콜릿류' );
INSERT INTO product VALUES ( 'B00005', '허쉬', 'box', '초콜릿류' );

delete from employee;
insert into employee values('E0001','1234','010-8838-0247','김현서','ksh98520@naver.com','1998-07-13','영업1','staff');
insert into employee values('E0002','1234','010-8838-0245','엄준식','ksh624@naver.com','1998-07-05','영업1','staff');
insert into employee values('E0003','1234','010-2634-0247','하인호','ksh5325@naver.com','1995-03-24','영업1','staff');
insert into employee values('E0004','1234','010-7457-0247','최인석','ksh93250@naver.com','1990-06-22','영업1','staff');
insert into employee values('E0005','1234','010-3067-4088','강병준','kbj010@naver.com','2002-03-29','영업1','manager');
select * from employee;