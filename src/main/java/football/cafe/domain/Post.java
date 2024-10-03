package football.cafe.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Post {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member writer;

    private String title;

    private String content;

    @OneToMany(mappedBy = "post")
    private List<Comment> comments = new ArrayList<>();

    private LocalDateTime postDate;

    public void updateContent(String content) {
        this.content = content;
    }

    public void addPost(Member writer, String title, String content) {
        this.writer = writer;
        this.title = title;
        this.content = content;
        postDate = LocalDateTime.now();
    }
}
