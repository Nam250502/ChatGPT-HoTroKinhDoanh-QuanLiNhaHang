package nam.nguyen.store.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {
    @GetMapping("/")
    public String test1(){
        return "index";
    }
    @GetMapping("/tableoder")
    public String test2(){
        return "table-oder";
    }
}
