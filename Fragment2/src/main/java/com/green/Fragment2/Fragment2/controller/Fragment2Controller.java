package com.green.Fragment2.Fragment2.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class Fragment2Controller {

    @GetMapping("/")
    public String first(Model model){
        model.addAttribute("name","홍길동");
        model.addAttribute("age",30);
        return "content/list";
    }
    @GetMapping("detail")
    public String list(){
        return "content/detail";
    }
}
