package football.cafe.domain;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    private String name;

    @OneToMany(mappedBy = "writer")
    private List<Post> posts = new ArrayList<>();

    private String email;

    private String password;

    @OneToMany(mappedBy = "writer")
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "writer")
    private List<Question> questions = new ArrayList<>();

    @OneToMany(mappedBy = "writer")
    private List<Answer> answers = new ArrayList<>();

    public void signUp(String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
    }
}
