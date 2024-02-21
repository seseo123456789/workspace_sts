package com.green.Second.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

//@Controller : 해당 클래스 파일이 컨트롤러 역할을 하는 클래스임을 spring에서 인식!
@Controller
public class MemberController {

    // @GetMapping @PostMapping
    // 페이지 접속 정보
    // 소괄호안의 글자와 localhost:8081 뒤의 글자가 일치하면 해당 메소드를 실행
    // @PostMapping 은 페이지 이동 방법 중에 form 태그로 이동하면서 form태그의 method 속성값이 post일때만 실행됨.
    // get 3방식: 'form' 태그의 method 속성값이 get일때 & 'a '태그로 페이지가 이동될때 & 주소창에 '직접' urt를 입력할때.
    // " / " 의미: localhost:8081 웹페이지 접속
    @GetMapping("/") // 첫페이지 생성
    public String main(){
        // 리턴되는 문자열은 마지막에 실행되는 html 파일명!
        // html 파일은 반드시 templates 폴더 안에 있어야됨
        return "first"; //first.html 실행!
    }

    // @ RequestParam: 이노테이션으로 html 에서 넘어오는 데이터를 전달
    // 해당 이노테이션의 속성으로는  name , required, defaultValue가 있다
    // name: html 에서 넘어오는 데이터의 이름
    // required : 넘어오는 데이터가 필수 데이터인지 설정 ( required= false : 데이터값이 없을때 null출력)
    // required  속성을 적지 않으면  default 값은 true
    // defaultValue :  데이터가 넘어오지 않은 때 설정되는 기본값(데이터값이 없을때 기본값을 유저가 설정할수 있다)

    //html로 데이터를 전달하기위해서
    // 메소드의 메개변수로 Model(인터페이스) 의 객체를 선언
    @GetMapping("/second")
    public String second(@RequestParam(name="addr", required = false)String address, @RequestParam(name = "age", required = false,
                         defaultValue = "1") int age, Model model){
        // @RequestParam: 넘어온다는 의미/ name: addr 이름으로 넘어오는 데이터/ string address :변수설정
        System.out.println("addr : " + address);
        System.out.println("age : " + age);


        // html로 데이터 전달하기
        model.addAttribute("addr",address);
        model.addAttribute("age",age);
        model.addAttribute("name","홍길동");


        return "second";
    }

}
