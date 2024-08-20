package football.cafe.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
public class Comment {
    @Id @GeneratedValue
    @Column(name = "comment_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member writer;

    private String content;

    private LocalDateTime date;

    @ManyToOne
    @JoinColumn(name = "post_id")
    private Post post;
}
