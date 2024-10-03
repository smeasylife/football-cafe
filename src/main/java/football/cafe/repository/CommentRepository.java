package football.cafe.repository;

import football.cafe.domain.Comment;
import football.cafe.domain.Post;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class CommentRepository {
    @PersistenceContext
    private final EntityManager em;

    public void save(Comment comment) {
        em.persist(comment);
    }

    public Comment findById(Long id) {
        return em.find(Comment.class, id);
    }

    public List<Comment> findAll() {
        return em.createQuery("select c from Comment c", Comment.class)
                .getResultList();
    }

    public void delete(Long id) {
        em.remove(findById(id));
    }
}
