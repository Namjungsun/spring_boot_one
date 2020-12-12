package hello.hello.spring.service;

import hello.hello.spring.repository.MemberRepository;
import hello.hello.spring.repository.MemoryMemberRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//자바 코드로 직접 스프링 빈 등록하기
//스프링이 뜰때 읽음
@Configuration
public class SpringConfig {

    //스프링빈에 등록 (컨트롤 + p 누르면 필요한 인자 알려줌)
    @Bean
    public MemberService memberService(){
        return new MemberService(memberRepository());
    }

    //인터페이스는 new 안됨 참고
    @Bean
    public MemberRepository memberRepository(){
        return new MemoryMemberRepository();
    }
}
