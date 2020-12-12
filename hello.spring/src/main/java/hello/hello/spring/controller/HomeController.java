package hello.hello.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // 첫번째 호출 -> resources/templates/home.html 호출
    @GetMapping("/")
    public String home(){
        return "home";
    }
}
