package ru.otus.spring.kreidun.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.spring.kreidun.models.Author;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class AuthorRepositoryImpl implements AuthorRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public int count() {

        TypedQuery<Author> query = em.createQuery("select a from Author a", Author.class);
        return query.getResultList().size();
    }

    @Override
    public Author save(Author author) {

        if (author.getId() <= 0) {
            em.persist(author);
            return author;
        } else {
            return em.merge(author);
        }
    }

    @Override
    public Author findById(long id) {
        return em.find(Author.class, id);
    }

    @Override
    public List<Author> getAll() {
        TypedQuery<Author> query = em.createQuery("select a from Author a", Author.class);
        return query.getResultList();
    }
}
