insert into AUTHORS(firstname, lastname)
values ('Александр','Пушкин'), ('Иван','Ефремов');

insert into GENRES(name)
values ('Роман в стихах'), ('Фантастика'), ('Детектив');

insert into BOOKS (title, genre_id, author_id)
values ('Евгений Онегин', 1,1),('Час Быка', 2,2);

insert into COMMENTS (text, book_id)
values ('Превосходно', 1),('Увлекательно', 1),('Не читал', 2);

insert into USERS (username, password, accountnonexpired, accountnonlocked, credentialsnonexpired, enabled)
values ('admin', '1', true, true, true, true);