package football.cafe.service;

import football.cafe.domain.Post;
import football.cafe.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PostService {
    private final PostRepository postRepository;

    public void save(Post post) {
        postRepository.save(post);
    }

    public void update(Long id, String content) {
        Post post = postRepository.findById(id);
        post.updateContent(content);
    }

    public void delete(Long id) {
        Post post = postRepository.findById(id);
        postRepository.delete(post);
    }

    public List<Post> findAllPost() {
        return postRepository.findAll();
    }

    public Post findPost(Long id) {
        return postRepository.findById(id);
    }
}
