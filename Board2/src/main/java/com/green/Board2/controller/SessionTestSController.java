package com.green.Board2.controller;

import com.green.Board2.vo.MemberVo;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

//Session : 모든 페이지에서 사용할 수 있는 데이터저장공간 단, 웹브라우저끼리는 공유가 안됨
// Model: 내가 저장한 데이터만 저장해서 html로 보내기

// Session 은 데이터 추가, 삭제는 자바에서만 가능
// Session 은 보기는  자바,html에서 가능  가능

@Controller
@RequestMapping("/test")
public class SessionTestSController {

    @GetMapping ("/page1")
    public String page1(HttpSession session){
        // 세션에 데이터 저장하기
        session.setAttribute("name","java");
        session.setAttribute("age",30);
        // html 페이지에서 session에 저장된 name을 이용가능  [[${session.name}]]
        session.setAttribute("member",new MemberVo());
        // html 페이지에서 session에 저장된 객체도 이용가능   [[${session.member}]]// 변수도 사용가능 [[${session.member.memberName}]]

        //세션 유지시간 설정 : 조단위  //-> 세션 데이터 보이는 유지시간 3초
        //session.setMaxInactiveInterval(3);

        //-> 세션 데이터 보이는 유지시간 30분
        session.setMaxInactiveInterval(60*30);

        return "page1";
    }

    @GetMapping("/page2")
    public String page2(HttpSession session){
        //세션에 담긴 데이터 확인
       //String name = (String)session.getAttribute("name");
        //int age = (int)session.getAttribute("age");
        //MemberVo member = (MemberVo) session.getAttribute("member");

        //세션 데이터 삭제
        session.removeAttribute("age");

        // 모든 세션 데이터 삭제
        //session.invalidate();

        return "page2";
    }

    @GetMapping("/page3")
    public String page3(){
        return "page3";
    }
}
