package com.green.First.controller;

import com.green.First.vo.Join;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class MemberController {

    @GetMapping("reg")
    public String regMember(){

        return "reg_member";
    }
// 회원 상세 정보 입력 페이지로 이동
// 커맨드 객체 : 컨드롤러의 메소드의 매개변수 중에서 넘어오는 데이터를 받기 위해 선언한 객체 goDetail(Join join)
// 커맨드 객체는 model을 사용하지 않아도 자동으로 html로 데이터가 전달됨.
//커맨드 객체가 자동으로 넘어갈때 넘어가는 데이터의 이름은 클래스명에서 앞글자만 소문자로 바꾼 이름으로 전달됨
    @PostMapping("goDetail")
    public String goDetail(Join join){
         System.out.println(join);
        return "reg_member_detail";


    }
    @PostMapping("goInfo")
    public String goInfo(Join join){
        System.out.println(join);
        return "member_info";
    }


}
