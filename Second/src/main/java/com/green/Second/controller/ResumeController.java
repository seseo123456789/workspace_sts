package com.green.Second.controller;

import com.green.Second.vo.ResumeVO;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ResumeController {

    @GetMapping("/res")
    public String resume(){

        return "resumeReg";

    }
    @PostMapping("/goDetail")
    public String goDetail(@RequestParam(name="name")String name, @RequestParam(name = "tel") String tel, Model model){

        System.out.println("n: " + name);
        System.out.println("t: " + tel);

        model.addAttribute("name",name);
        model.addAttribute("tel",tel);

        return "resumeInfo";
    }
    @PostMapping("/goInfo")
    public String goInfo(ResumeVO resumeVO){

        System.out.println(resumeVO);

        return "resumeCheck";
    }

}


