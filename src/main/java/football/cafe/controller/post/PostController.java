package football.cafe.controller.post;

import football.cafe.domain.Member;
import football.cafe.domain.Post;
import football.cafe.dto.PostForm;
import football.cafe.service.PostService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDateTime;
import java.util.List;

@Controller
@RequiredArgsConstructor
@Slf4j
public class PostController {
    private final PostService postService;
    private final HttpSession httpSession;

    @GetMapping("/")
    public String home(Model model, HttpSession session) {
        model.addAttribute("loginMember", session.getAttribute("loginMember"));
        return "index";
    }

    @GetMapping("/write-news")
    public String writeNews(Model model) {
        model.addAttribute("postForm", new PostForm());
        return "write-news";
    }

    @PostMapping("write-news")
    public String writeNews(@Validated @ModelAttribute PostForm postForm, BindingResult bindingResult, Model model, HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "write-news";
        }

        Member writer = (Member) httpSession.getAttribute("loginMember");
        Post post = new Post();
        post.addPost(writer, postForm.getTitle(), postForm.getContent());

        postService.save(post);
        return "redirect:/news";
    }

    @GetMapping("/news")
    public String news(Model model) {
        List<Post> posts = postService.findAllPost();
        model.addAttribute("posts", posts);
        return "news";
    }

    @GetMapping("post/{id}")
    public String post(Model model, @PathVariable Long id) {
        Post post = postService.findPost(id);
        model.addAttribute("post", post);
        return "post";
    }

    @GetMapping("/error")
    public String error(Model model) {
        return "error/404";
    }
}
