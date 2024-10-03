package football.cafe.service;

import football.cafe.domain.Comment;
import football.cafe.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class CommentService {
    private final CommentRepository commentRepository;

    public Long save(Comment comment) {
        commentRepository.save(comment);
        return comment.getId();
    }

    public void delete(Long id) {
        commentRepository.delete(id);
    }

    public Comment findComment(Long id) {
        return commentRepository.findById(id);
    }
}
