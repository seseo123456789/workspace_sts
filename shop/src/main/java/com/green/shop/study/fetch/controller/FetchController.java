package com.green.shop.study.fetch.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.green.shop.item.vo.ImgVo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.yaml.snakeyaml.events.Event;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/fetch")
public class FetchController {

    @GetMapping("/main")
    public String main(){
        return "test/fetch/main";
    }
    
// -----------------비동기 fetch 두번째방식 STUDY
    // 넘어오는 데이터를 받을 때 사용하는 어노테이션은 두가지가 있다
    // 1. @RequestParam : url에 데이터가 함께 넘어올 때 사용
            // ex) localhost:8081/aaa?a=b 주소경로가 보이는게 url
            // ex) form 태그를 사용하면 눈에 안보이지만 데이터가 넘어옴


    // 2. @RequestBody : url이 아닌 body 영역에 데이터가 담겨서 올 때


    //VO 써서 데이터 받아오기
    @ResponseBody
    @PostMapping("/fetch1")
    public void fetch1(@RequestBody MemberVO memberVO){
        System.out.println("fetch1 메서드 실행!!");
        System.out.println(memberVO);
    }


    // map 써서 데이터 받아오기
    @ResponseBody
    @PostMapping("/fetch2")
    public void fetch2(@RequestBody HashMap<String, String> data){
        System.out.println("fetch2 메서드 실행!!!!!");
        System.out.println(data);
        System.out.println(data.get("id"));
        System.out.println(data.get("name"));
        System.out.println(data.get("age"));
    }

// 객체 배열의 데이터 받기
    // 자바스크립트에서 배열이 받아오면 Arraylist 로 받을 수 있음.
    @ResponseBody
    @PostMapping("/fetch3")
    public void fetch3(@RequestBody ArrayList<MemberVO> list){

        System.out.println("fetch3 메서드 실행!!!!!");
        System.out.println(list);

    }

    @ResponseBody
    @PostMapping("/fetch4")
    public void fetch4(@RequestBody HashMap<String, Object> map) {

        System.out.println("fetch4 메서드 실행!!!!!");
        System.out.println(map);

        // cartCode 출력
        // cartCode 가 자료형 Object 로 받기때문에 int로 형변경시켜줘야됨.(Object로 받으면 자료는 받지만 기능을 못함)
        int j = (int) map.get("cartCode");
        System.out.println("cartCode:" + j);

        // ----- memberId 출력

        HashMap<String, String> member = (HashMap<String, String>) map.get("memberInfo");
        String memberId = member.get("memberId");
        System.out.println("memberId:" + memberId);

        //----- imgCode 출력

        //1. itemInfo 출력
        Map<String, Object> item = (Map<String, Object>) map.get("itemInfo");
        System.out.println("itemInfo 출력!!!");
        System.out.println("itemInfo:" + item);

        //2. itemInfo의 imgList 출력
        List<Object> img = (List<Object>) item.get("imgList");
        System.out.println("imgList 출력!!!!!");
        System.out.println("imgList:" + img);  //imgList[{Map},{Map}]

        //3.imgList[0] 의 imgCode 출력
        //** imgList[{Map},{Map}] (Map 의 출력표시 -->{ } )
        Map<String, Object> img1 = (Map<String, Object>) img.get(0);
        int imgCode1 = (int) img1.get("imgCode");
        System.out.println("첫번째 imgCode 출력!!!!!!!!");
        System.out.println("[0]의 imgCode:" + imgCode1);

        System.out.println("////////////////////////////////////");
        // ///////////////////////////////////////////////////////////////////////////////////

        // Map 데이터를 VO(클래스)객체에 매핑하기
        ObjectMapper mapper = new ObjectMapper();
        //convertValue(총데이터명, 총데이터변수담은VO명.class);
        MapDataVO data = mapper.convertValue(map, MapDataVO.class);
        System.out.println("출력~~~~~~~~~~");
        System.out.println(data);




    }
}
