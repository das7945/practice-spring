package DHhome.practicespring.service;

import static org.junit.jupiter.api.Assertions.assertThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import DHhome.practicespring.controller.domain.Member;
import DHhome.practicespring.repository.MemoryMemberRepository;

@SpringBootTest
// @Transactional
// DB에는 기본적으로 트랜잭션이라는 개념이 있는데
// 인서트 -> 컴잇 -> 롤백 순으로 작동하게 된다.
// test의 경우에는 테스트 용도로 DB에 연결하여 작동여부를 살피는데
// test 시작 전에 @Transactional 먼저 시작하고
// 마지막에 test종료 후, 사용한 test데이타는 롤백으로 사라진다.
// 이로 인해 얻는 이점은 다음 test에 영향을 주지 않는다.
// 각 메서드 마다 적용.
public class MemberServiceIntergrantionTest {

  @Autowired  MemberService memberService;
  @Autowired  MemoryMemberRepository memberRepository;


  @Test
  void 회원가입() {
    Member member = new Member();
    member.setName("spring");

    Long saveId = memberService.join(member);

    Member findMember = memberService.findOne(saveId).get();
    Assertions.assertThat(member.getName()).isEqualTo(findMember.getName());
    System.out.println("멤버이름: " + member.getName());
    System.out.println("저장데이터맴버이름: " + findMember.getName());
  }

  @Test
  public void 중복_회원_예외() {
    Member member1 = new Member();
    member1.setName("spring");

    Member member2 = new Member();
    member2.setName("spring");

    memberService.join(member1);
    IllegalStateException e = assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재하는 회원입니다.");

    //    try {
    //      memberService.join(member2);
    //      //      fail();
    //    } catch (IllegalStateException e) {
    //      Assertions.assertThat(e.getMessage()).isEqualTo("이미 존재23하는 회원입니다.");
    //    }
  }

}
