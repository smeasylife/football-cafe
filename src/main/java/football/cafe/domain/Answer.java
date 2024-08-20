package football.cafe.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDateTime;

@Entity
@Getter
public class Answer {
    @Id @GeneratedValue
    @Column(name = "answer_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "question_id")
    private Question question;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member writer;

    private String content;

    private LocalDateTime date;
}
