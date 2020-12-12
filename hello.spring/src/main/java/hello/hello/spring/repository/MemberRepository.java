package hello.hello.spring.repository;

import hello.hello.spring.domain.Member;

import java.util.List;
import java.util.Optional;

public interface MemberRepository {
    /* save하면 저장소에 저장 */
    Member save(Member member);

    /* Optional. NULL이 반환되는 경우를 대비해서 감싸서 반환. */
    /* 찾음*/
    Optional<Member> findById(Long id);
    Optional<Member> findByName(String name);

    /* 찾은것을 리스트로 반환 */
    List<Member> findAll();
}
