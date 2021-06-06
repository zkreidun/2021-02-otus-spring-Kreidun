insert into AUTHORS(firstname, lastname)
values ('Александр','Пушкин'), ('Иван','Ефремов');

insert into GENRES(name)
values ('Роман в стихах'), ('Фантастика'), ('Детектив');

insert into BOOKS (title, genre_id, author_id)
values ('Евгений Онегин', 1,1),('Час Быка', 2,2);

insert into COMMENTS (text, book_id)
values ('Превосходно', 1),('Увлекательно', 1),('Не читал', 2);

insert into USERS (username, password, accountnonexpired, accountnonlocked, credentialsnonexpired, enabled)
values ('admin', '1', true, true, true, true), ('user', '1', true, true, true, true), ('test', '1', true, true, true, true);

INSERT INTO acl_sid (id, principal, sid) VALUES
(1, 1, 'admin'),
(2, 1, 'user'),
(3, 1, 'test');

INSERT INTO acl_class (id, class) VALUES
(1, 'ru.otus.spring.kreidun.models.Author'),
(2, 'ru.otus.spring.kreidun.models.Book'),
(3, 'ru.otus.spring.kreidun.models.Comment'),
(4, 'ru.otus.spring.kreidun.models.Genre');

INSERT INTO acl_object_identity (id, object_id_class, object_id_identity, parent_object, owner_sid, entries_inheriting) VALUES
(1, 2, 1, NULL, 1, 0),
(2, 2, 2, NULL, 1, 0);

INSERT INTO acl_entry (id, acl_object_identity, ace_order, sid, mask, granting, audit_success, audit_failure) VALUES
(1, 1, 1, 1, 1, 1, 1, 1),
(2, 1, 2, 1, 2, 1, 1, 1),
(3, 1, 3, 3, 1, 0, 1, 1),
(4, 1, 4, 3, 2, 0, 1, 1),
(5, 1, 5, 2, 1, 1, 1, 1),
(6, 2, 2, 1, 1, 1, 1, 1),
(7, 2, 3, 1, 2, 1, 1, 1),
(8, 2, 4, 2, 1, 0, 1, 1),
(9, 2, 5, 3, 1, 0, 1, 1);