package DHhome.practicespring.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import DHhome.practicespring.controller.domain.Member;
import DHhome.practicespring.service.MemberService;

@Controller
public class MemberController {

  private MemberService memberService;

  @Autowired
  public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }

  @GetMapping("/members/new")
  public String createForm() {
    return "members/createMemberForm";

  }


  // 클라이언트에서 서버로 name이라는 키값으로 요청데이터가 넘어 올 경우
  // 인자로 받고있는 객체타입 ??파일에서?? name이라는 이름을 우선순위로 찾는다.
  // MemberForm파일에 어노테이션이 없는 경우에도 스프링MVC가 지원하는 기능으로 가능함.
  @PostMapping("/members/new")
  public String create(MemberForm form) {
    Member member = new Member();
    member.setName(form.getName());

    memberService.join(member);

    return "redirect:/";
  }


  // Model은 Spring MVC에서 컨트롤러(Java 코드)와 뷰(템플릿) 사이에
  // 데이터를 전달하는 데 사용되는 객체
  @GetMapping("/members/")
  public String list(Model model) {
    List<Member> members = memberService.findMember();
    model.addAttribute("members",members);
    return "members/memberList";
  }

}
