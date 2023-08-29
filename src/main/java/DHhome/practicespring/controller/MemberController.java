package DHhome.practicespring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import DHhome.practicespring.service.MemberService;

@Controller
public class MemberController {

  private MemberService memberService;

  @Autowired
  public MemberController(MemberService memberService) {
    this.memberService = memberService;
  }

}
