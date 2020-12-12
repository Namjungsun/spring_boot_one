package hello.hello.spring.repository;

import hello.hello.spring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

/* implements 상속 */
// @Repository 하면 스프링 컨테이너에 등록해줌.
// @Repository
public class MemoryMemberRepository implements MemberRepository{

    /* static(공유되는) 메모리에 저장. 여기서는 MAP을 씀 */
    private static Map<Long, Member> store = new HashMap<>();
    /* key값 생성. 시퀀스 */
    private static long sequence = 0L;

    @Override
    public Member save(Member member) {
        /* 멤버를 저장할때 일딴 시퀀스 값을 올려줌 */
        member.setId(++sequence);
        /* 맵에 저장 */
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        /* null일 경우 대비해서 Optional로 감싸서 반환 */
        return Optional.ofNullable(store.get(id));
    }

    @Override
    public Optional<Member> findByName(String name) {
        /* 람다 사용. 루프로 돌리는거 찾으면 그것만 반환. 못차즈면 Optional로 감싼 널 반환 */
        /* 함수인데 함수를 따로 만들지 않고 코드한줄에 함수를 써서 그것을 호출하는 방식 */
        return store.values().stream()
                .filter(member -> member.getName().equals(name))
                .findAny();
    }

    @Override
    public List<Member> findAll() {
        /* values하면 리스트에있는 맴버들이 반환. */
        return new ArrayList<>(store.values());
    }

    /* 클리어하는 메소드 작성 */
    public void clearStore(){
        store.clear();
    }
}
