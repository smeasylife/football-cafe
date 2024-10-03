package football.cafe.service;

import football.cafe.domain.Member;
import football.cafe.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;


@SpringBootTest
@Transactional
public class MemberServiceTest {

    @Autowired MemberService memberService;
    @Autowired MemberRepository memberRepository;

    @Test
    public void joinTest() {
        Member member = new Member();
        member.signUp("hwang", "sdsd2223@naver.com", "ksksksk");

        Long id = memberService.join(member);

        Assertions.assertEquals(member, memberRepository.findById(id));
    }
}
