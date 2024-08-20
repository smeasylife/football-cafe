package football.cafe.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
public class Question {
    @Id @GeneratedValue
    @Column(name = "question_id")
    private Long id;

    private String title;

    private String content;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member writer;

    @OneToMany(mappedBy = "question")
    private List<Answer> answers;

    private LocalDateTime date;
}
