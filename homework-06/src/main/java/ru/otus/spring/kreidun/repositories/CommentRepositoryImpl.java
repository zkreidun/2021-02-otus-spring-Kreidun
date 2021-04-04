package ru.otus.spring.kreidun.repositories;

import org.springframework.stereotype.Repository;
import ru.otus.spring.kreidun.models.Comment;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class CommentRepositoryImpl implements CommentRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public int count() {

        TypedQuery<Comment> query = em.createQuery("select c from Comment c", Comment.class);
        return query.getResultList().size();
    }

    @Override
    public Comment save(Comment comment) {

        if (comment.getId() <= 0) {
            em.persist(comment);
            return comment;
        } else {
            return em.merge(comment);
        }
    }

    @Override
    public Comment findById(long id) {
        return em.find(Comment.class, id);
    }

    @Override
    public List<Comment> getAll() {

        TypedQuery<Comment> query =  em.createQuery("select c from Comment c " +
                "join fetch c.book b " +
                "join fetch b.author a ",
                Comment.class);
        return query.getResultList();
    }

    @Override
    public void deleteById(long id) {

        Comment comment = em.find(Comment.class, id);
        em.remove(comment);
    }
}
