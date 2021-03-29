package ru.otus.spring.kreidun.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.otus.spring.kreidun.models.Book;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Transactional
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

        Query query = em.createQuery("delete " +
                "from Book b " +
                "where b.id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    @Override
    public void update(long id, String bookTitle, long authorId, long genreId) {

        Query query = em.createQuery("update " +
                "Book b " +
                "set b.title = :title " +
                ", b.author = (select a from Author a where a.id = :author) " +
                ", b.genre = (select g from Genre g where g.id = :genre) " +
                "where b.id = :id");
        query.setParameter("id", id);
        query.setParameter("title", bookTitle);
        query.setParameter("author", authorId);
        query.setParameter("genre", genreId);
        query.executeUpdate();
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
