package hello.hello.spring.controller;

import hello.hello.spring.domain.Member;
import hello.hello.spring.service.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.awt.*;
import java.util.List;

//스프링이 뜰때 스프링 컨테이너라는 통이 생김.
//스프링이 MemberController를 객체를 생성해서 관리.
//스프링컨테이너에 스프링빈이 관리된다고 표현.
//스프링 빈을 등록하는 방법 : 컴포넌트 스캔과 자동 의존관계 설정
@Controller
public class MemberController {
    // 스프링 컨테이너에 하나만 등록
    private final MemberService memberService;

    //생성자 호출 되면 Autowired 있으면 스프링에 있는 맴버서비스랑 연결. 디펜던시 인잭션.
    @Autowired
    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }

    //GetMapping은 조회할때 주로 씀.
    @GetMapping("/members/new")
    public String crateForm(){
        //members/createMemberForm.html로 이동
        return "members/createMemberForm";
    }

    // PostMapping은 보통 데이터를 폼같은데 넣어서 전달할때(등록) 주로 씀.
    @PostMapping("/members/new")
    public String create(MemberForm form){
        Member member = new Member();
        member.setName(form.getName());

        System.out.println("member = " + member.getName());

        memberService.join(member);

        //홈화면으로 돌려 보냄
        return "redirect:/";
    }

    @GetMapping("/members")
    public String list(Model model){
        List<Member> members = memberService.findMembers();
        model.addAttribute("members",members);
        // resources/templates/members/memberList.html로 보냄
        return "members/memberList";
    }
}
