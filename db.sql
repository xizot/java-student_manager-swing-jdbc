CREATE DATABASE STUDENT_MANAGER
go 
USE STUDENT_MANAGER
go

CREATE TABLE STUDENT
(
	Id varchar(20) primary key,
	Name nvarchar(30),
	Point float(2),
	Image varchar(100),
	Address varchar(100),
	Note text
)

go
INSERT INTO STUDENT VALUES('ID01','John','9','c.jpg','District 9','Nothing');
INSERT INTO STUDENT VALUES('ID02','Smith','10','d.jpg','District 10','Nothing');
INSERT INTO STUDENT VALUES('ID03','Cena','8','e.jpg','District 11','Nothing');
INSERT INTO STUDENT VALUES('ID04','Ceo','7','f.jpg','District 12','Nothing');
