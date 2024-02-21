package com.green.Board2.controller;

import com.green.Board2.service.MemberService;
import com.green.Board2.service.MemberServiceImpl;
import com.green.Board2.vo.MemberVo;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/member")
public class MemberController {

     @Resource(name = "memberService")
     private MemberServiceImpl memberService;

    //로그인 페이지로 이동하기
    @GetMapping("/loginForm")
    public  String loginForm(){

        return "login";
    }
    //가입 페이지로 이동
    @GetMapping("/goReg")
    public String goReg(){
        return "join";
    }
    // 저장
    @PostMapping("/join")
    public String join(MemberVo memberVo) {
        memberService.memberReg(memberVo);
        return "redirect:/member/loginForm";
    }

    //로그인하기
    @PostMapping("/login")
    public String login(MemberVo memberVo, HttpSession session){
       MemberVo loginInfo = memberService.login(memberVo);

       //로그인 정보가 조회가 됬으면 로그인시켜주기
        if(loginInfo !=null){
            //세션에 로그인 정보 저장
            session.setAttribute("loginInfo", loginInfo);
        }
        return "login_result";
    }

    //로그아웃
    @GetMapping("logout")
    public String logout(HttpSession session){
        session.removeAttribute("loginInfo");
        return "redirect:/board/list";
    }
}
