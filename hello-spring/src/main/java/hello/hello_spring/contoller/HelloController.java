package hello.hello_spring.contoller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HelloController {

    @GetMapping("hello")    // 웹 애플리케이션에서 /hello
    public String hello(Model model) {
        model.addAttribute("data", "spring!!");
        return "hello";
    }
}
