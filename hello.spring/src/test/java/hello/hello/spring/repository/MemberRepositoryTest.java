package hello.hello.spring.repository;

import hello.hello.spring.domain.Member;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

/* 테스트를 먼저 만들고 구현클래스를 만드는것 테스트 주도 개발 TDD. */

/* 어디서 같다 쓸거 아니어서 public 아니어도 됨 */
class MemberRepositoryTest {
    MemoryMemberRepository repository = new MemoryMemberRepository();

    /* 하나의 테스트가 종료되면 클리어 해야 다른 테스트에 영향이 없음 */
    /* 메소드가 끝날때마다 실행 (콜백 메소드) 어노테이션 */
    @AfterEach
    public void afterEach(){
        repository.clearStore();
    }


    /* 테스트 모듈 어노테이션 */
    @Test
    public void save(){
        Member member = new Member();
        member.setName("spring");

        /* 저장소에 저장 */
        repository.save(member);

        /* 저장소에 확인 */
        Member result = repository.findById(member.getId()).get();

        /* sysout으로 출력하기
        System.out.println("result = " +(result = member)); */

        /* 성공 시 */
        /* assert로 확인 이상 없으면 녹색불, 이상있으면 빨간 불 */
        /* Assertions.assertEquals(member, result); */

        /* 실패 시 */
        /* Assertions.assertEquals(member, null); */

        /* core쪽 Assertions 사용 */
        Assertions.assertThat(member).isEqualTo(result);
    }

    @Test
    public void findByName(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        Member result = repository.findByName("spring1").get();
        Assertions.assertThat(result).isEqualTo(member1);
    }

    @Test
    public void findAll(){
        Member member1 = new Member();
        member1.setName("spring1");
        repository.save(member1);

        Member member2 = new Member();
        member2.setName("spring2");
        repository.save(member2);

        List<Member> result = repository.findAll();
        Assertions.assertThat(result.size()).isEqualTo(2);
    }
}
