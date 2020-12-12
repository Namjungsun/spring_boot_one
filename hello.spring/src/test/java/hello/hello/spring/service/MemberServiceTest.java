package hello.hello.spring.service;

import hello.hello.spring.domain.Member;
import static org.assertj.core.api.Assertions.*;

import hello.hello.spring.repository.MemoryMemberRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MemberServiceTest {

    MemberService memberService;
    MemoryMemberRepository memberRepository;

    /* 메소드 시작하기전에 실행 */
    @BeforeEach
    public void beforeEach(){
         memberRepository = new MemoryMemberRepository();
         memberService = new MemberService(memberRepository);
    }

    /* 하나의 테스트가 종료되면 클리어 해야 다른 테스트에 영향이 없음 */
    /* 메소드가 끝날때마다 실행 (콜백 메소드) 어노테이션 */
    @AfterEach
    public void afterEach(){
        memberRepository.clearStore();
    }

    @Test
    void 회원가입() {
        //given (상황이 주어졌을때)
        Member member = new Member();
        member.setName("hello");

        //when (했을때)
        Long saveId = memberService.join(member);

        //then (결과)
        Member findMember = memberService.findOne(saveId).get();
        assertThat(member.getName()).isEqualTo(findMember.getName());

    }

    @Test
    public void 중복_회원_예외(){
        //given
        Member member1 = new Member();
        member1.setName("spring");

        Member member2 = new Member();
        member2.setName("spring");

        //when
        memberService.join((member1));
        IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
        assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

//        try{
//            memberService.join((member2));
//            //테스트 실패
//            fail();
//        }catch (IllegalStateException e){
//            assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");
//        }


        //then


    }

    @Test
    void findMembers() {
    }

    @Test
    void findOne() {
    }
}