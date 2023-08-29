package DHhome.practicespring.service;


import static org.junit.jupiter.api.Assertions.assertThrows;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import DHhome.practicespring.controller.domain.Member;
import DHhome.practicespring.repository.MemoryMemberRepository;

class MemberserviceTest {

  MemberService memberService;
  MemoryMemberRepository memberRepository;

  @BeforeEach // 각 메서드가 실행되기 전 실행되는 메서드
  public void beforeEach() {
    memberRepository = new MemoryMemberRepository();
    memberService = new MemberService(memberRepository);
  }

  @AfterEach // 각 메서드가 실행된 후 실행되는 메서드
  public void afterEach() {
    memberRepository.clearStore();
  }


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
