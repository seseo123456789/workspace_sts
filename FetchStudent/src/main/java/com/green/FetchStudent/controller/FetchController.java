package com.green.FetchStudent.controller;

import com.green.FetchStudent.vo.StuVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

// 비동기 통신 학습용 컨트롤러
// javascript : fetch 라는 걸 써서 비동기로 만든다
// jquery : ajax 라는 걸 써서 비동기로 만든다 $()
// react : axios 라는 걸 써서 비동기로 만든다
@Controller
@RequestMapping("/study")
public class FetchController {

     @GetMapping("/t1")
     public String t1(){
         return "fetch_test";
     }

     // @ResponseBody : 해당 메소드는 비동기 통신을 사용하기 때문에
    //메소드의 마지막 return으로 페이지 전환 하지 않겠다를 스프링에게 알려주는 코드
     @ResponseBody
     @PostMapping("/t2")
    public int t2(@RequestParam(name="name")String name,
                  @RequestParam(name = "age") int age){
         System.out.println("t1 실행12");
         System.out.println("name= "+name);
         System.out.println("age= "+age);
         return 100;
     }

     @ResponseBody
     @PostMapping("/t3")
     public StuVo t3(StuVo vo){

         System.out.println(vo);
         System.out.println("t3메소드 실행");

         StuVo stuVo=new StuVo();

         stuVo.setStuNum(1);
         stuVo.setStuName("김자바");
         stuVo.setClassCode(1);
         stuVo.setClassName("자바반");

         return stuVo;
     }


     @ResponseBody
     @PostMapping("/t4")
     public List<StuVo> t4(){
         System.out.println("t4메소드 실행");

         List<StuVo> list = new ArrayList<>();
             for(int i= 1; i<6; i++) {
                 StuVo stuVo = new StuVo();

                 stuVo.setStuNum(i);
                 stuVo.setStuName("자바" + i);
                 stuVo.setClassCode(1 +i);
                 stuVo.setClassName("자바반_" +i);

                 list.add(stuVo);
             }
      return list;
     }


}
