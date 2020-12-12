package hello.hello.spring.service;

import hello.hello.spring.domain.Member;
import hello.hello.spring.repository.MemberRepository;
import hello.hello.spring.repository.MemoryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// Service 해주면 스프링이 올라올때 MemberService를 컨테이너에 등록
// @Service
public class MemberService {

    // 저장소 생성
    private final MemberRepository memberRepository;

    // 직접 new 하지 않고 외부에서 넣어줌. 디펜던시 인잭션. DI
    //생성자 호출 되면 Autowired 있으면 스프링에 있는 맴버서비스랑 연결. 디펜던시 인잭션. (생성자 주입 권장)
    // @Autowired
    public MemberService(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    // 회원 가입
    public Long join(Member member){
        // 같은 이름이 있는 중복회원 x
        validateDuplicateMember(member); //중복 회원 검증

        memberRepository.save(member);
        return member.getId();
    }

    private void validateDuplicateMember(Member member){
        Optional<Member> result = memberRepository.findByName(member.getName());
        // 만약 값이 있으면
        result.ifPresent(m -> {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        });
    }

    //전체 회원 조회
    public List<Member> findMembers(){
        return memberRepository.findAll();
    }

    public Optional<Member> findOne(Long memberId){
        return memberRepository.findById(memberId);
    }
}
