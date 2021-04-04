package ru.otus.spring.kreidun.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.spring.kreidun.models.Genre;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class GenreRepositoryImpl implements GenreRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public int count() {

        TypedQuery<Genre> query = em.createQuery("select g from Genre g", Genre.class);
        return query.getResultList().size();
    }

    @Override
    public Genre save(Genre genre) {

        if (genre.getId() <= 0) {
            em.persist(genre);
            return genre;
        } else {
            return em.merge(genre);
        }
    }

    @Override
    public Genre findById(long id) {
        return em.find(Genre.class, id);
    }

    @Override
    public List<Genre> getAll() {

        TypedQuery<Genre> query = em.createQuery("select g from Genre g", Genre.class);
        return query.getResultList();
    }
}
