package DHhome.practicespring;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import DHhome.practicespring.repository.MemberRepository;
import DHhome.practicespring.repository.MemoryMemberRepository;
import DHhome.practicespring.service.MemberService;

@Configuration
public class SpringConfig {

  @Bean
  public MemberService memberService() {
    return new MemberService(memberRepository());
  }

  @Bean
  public MemberRepository memberRepository() {
    return new MemoryMemberRepository();
  }
}
