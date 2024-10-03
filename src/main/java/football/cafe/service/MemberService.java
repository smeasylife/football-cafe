package football.cafe.service;

import football.cafe.domain.Member;
import football.cafe.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member);
        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member) {
        if (memberRepository.findByEmail(member.getEmail()) != null) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public List<Member> findAll() {
        return memberRepository.findAll();
    }

    public Member findMember(Long id) {
        return memberRepository.findById(id);
    }

    public Member findMemberByEmail(String email) {
        return memberRepository.findByEmail(email);
    }

    public Member login(String email, String password) {
        Member member = memberRepository.findByEmail(email);
        if (member == null) {
            return null;
        }

        if (!member.getPassword().equals(password)) {
            return null;
        }
        return member;
    }
}
