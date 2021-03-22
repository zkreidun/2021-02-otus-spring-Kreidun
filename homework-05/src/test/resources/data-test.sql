insert into GENRES (`NAME`) values ('Роман в стихах');
insert into GENRES (`NAME`) values ('Фантастика');
insert into GENRES (`NAME`) values ('Детектив');
insert into AUTHORS (`FIRSTNAME`,`LASTNAME`) values ('Александр','Пушкин');
insert into AUTHORS (`FIRSTNAME`,`LASTNAME`) values ('Иван','Ефремов');
insert into BOOKS (`TITLE`, GENREID, AUTHORID) values ('Евгений Онегин', 1, 1);
insert into BOOKS (`TITLE`, GENREID, AUTHORID) values ('Час Быка', 2, 2);