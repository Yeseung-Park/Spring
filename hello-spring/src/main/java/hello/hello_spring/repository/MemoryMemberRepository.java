package hello.hello_spring.repository;

import hello.hello_spring.domain.Member;
import org.springframework.stereotype.Repository;

import java.util.*;

public class MemoryMemberRepository implements MemberRepository{

    private static Map<Long, Member> store = new HashMap<>();
    // store는 현재 DB가 지정이 되지 않았기 때문에 DB 역할을 하는 임시 저장소의 역할을 한다.
    // DB가 지정이 되면 이 자리는 DB가 차지를 하겠지.
    // Map은 파이썬의 딕셔너리와 같은 느낌으로 생각하면 된다.
    // 데이터를 키-값 쌍으로 저장하고 관리할 수 있는 구조
    // 여기서는 키: Long -> 각 Member 객체를 구분하기 위한 고유 식별자
    // 값: Member -> Member 객체 자체
    private static long sequence = 0L;
    // squence는 멤버의 고유 ID를 생성하기 위한 변수

    @Override
    public Member save(Member member) {
        member.setId(++sequence);
        // sequence의 값을 하나씩 증가시키면서 member.setId의 인자로 넘겨준다.
        // 이렇게 함으로써 저장된 멤버마다 고유한 ID가 보장된다.
        // pk같은 느낌인거지
        store.put(member.getId(), member);
        return member;
    }

    @Override
    public Optional<Member> findById(Long id) {
        return Optional.ofNullable(store.get(id));
        // Optional로 감싸면 null 값이 반환되었을 때도 적절한 동작을 할 수 있게 한다.
    }

    @Override
    public List<Member> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public Optional<Member> findByName(String name) {
        return store.values().stream().filter(member -> member.getName().equals(name)).findAny();
    }

    public void clearStore() {
        store.clear();
    }
}
