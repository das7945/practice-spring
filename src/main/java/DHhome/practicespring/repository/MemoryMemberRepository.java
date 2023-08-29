package DHhome.practicespring.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import DHhome.practicespring.controller.domain.Member;


public class MemoryMemberRepository implements MemberRepository{

  private static Map<Long, Member> store = new HashMap<>();
  private static long sequence = 0L;

  @Override
  public Member save(Member member) {
    member.setId(++sequence);
    store.put(member.getId(), member);
    System.out.println("Join method memberId: " + member.getId());
    System.out.println("Join method memberName: "+ member.getName());
    return member;
  }

  @Override
  public Optional<Member> findById(Long id) {
    return Optional.ofNullable(store.get(id));
  }

  @Override
  public Optional<Member> findByName(String name) {
    return store.values().stream()
        .filter(member -> member.getName().equals(name))
        .findAny();
  }

  @Override
  public List<Member> findAll() {
    return new ArrayList<>(store.values());
  }

  //MemoryMemberRepositoryTest에서 사용하기 위한 메서드
  public void clearStore() {
    store.clear();
    // store의 맵 데이터를 지우는 역할
  }

}
