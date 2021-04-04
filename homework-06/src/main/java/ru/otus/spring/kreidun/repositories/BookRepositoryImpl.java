package ru.otus.spring.kreidun.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.spring.kreidun.models.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class BookRepositoryImpl implements BookRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Book getById(long id) {
        return em.find(Book.class, id);
    }

    @Override
    public int count() {

        TypedQuery<Book> query = em.createQuery("select b from Book b", Book.class);
        return query.getResultList().size();
    }

    @Override
    public Book save(Book book) {

        if (book.getId() <= 0) {
            em.persist(book);
            return book;
        } else {
            return em.merge(book);
        }
    }

    @Override
    public void deleteById(long id) {

        Book book = em.find(Book.class, id);
        em.remove(book);
    }

    @Override
    public List<Book> getAll() {

        TypedQuery<Book> query = em.createQuery("select b " +
                        "from Book b  " +
                        "join fetch b.author " +
                        "join fetch b.genre ",
                Book.class);
        return query.getResultList();
    }
}
