package hello.hello.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/* 컨트롤러 외부에서 처음 진입하는 곳 */
@Controller
public class HelloController {

    /* /hello로 들어오면 해당 메소드 호출 */
    @GetMapping("hello")
    public String hello(Model model){
        model.addAttribute("data","hello!!");
        /* resources/templates/hello를 찾아서 실행해라.*/
        return "hello";
    }
    /* view가 있음 */
    @GetMapping("hello-mvc")
    public String helloMvc(@RequestParam("name") String name, Model model){
        // 외부에서 받음...파라매터
        model.addAttribute("name", name);
        return "hello-template";
    }

    /* API 방식 */
    /* view가 없음 */
    /* ResponseBody 어노테이션은 html body에 직접 데이터를 넣겠다. */
    @GetMapping("hello-spring")
    @ResponseBody
    public String helloString(@RequestParam("name") String name){
        return "hello " + name;
    }

    /* 클래스안에 객체를 선언해서 사용하기 위해 클래스를 쓰려면 안에 있는 클래스를 static으로 선언*/
    /* 객체를 리턴해서 넘김 */
    /* json으로 나옴. 키 벨류로 되어있는 구조 */
    @GetMapping("hello-api")
    @ResponseBody
    public Hello HelloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    /* 개터 세터 자바빈 규약 - 외부 접근 방지 (프로퍼티 접근 방식) */
    static class Hello{
        private String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
