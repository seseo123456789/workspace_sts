package com.green.StudentManager.controller;

import com.green.StudentManager.service.StuServiceImpl;
import com.green.StudentManager.vo.StuVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class StudentController {
    @Resource(name="stuService")
    private StuServiceImpl stuService;


    //학생 목록 페이지로 이동
    @GetMapping("/")
    public String stuList(Model model){
        //학생 목록 조회
        List<StuVO> list =stuService.selectStuList();
        // 조회한 목록을 html로 전달
        model.addAttribute("stuList", list);
        return "stu_list";
    }
    @GetMapping("/regStu")
    public String regStu(){
        return "reg_student";
    }

    @PostMapping("/regStu")
    public String reg(StuVO stuVO){
        //학생 등록
        stuService.insertStu(stuVO);

        // controller 의 "/" 있는 메소드를 실행하라
        return "redirect:/";
    }

    @GetMapping("/stuDetail")
    public String stuDetail(@RequestParam(name="stuNo")int stuNo, Model model){
        //학생의 상세정보조회
        StuVO stu =stuService.selectStu(stuNo);
        //조회한 데이터 html로 전달
        model.addAttribute("stuInfo", stu);

        return "student_detail";
    }

    // StuVO stuVO 로 할때<=@RequestParam(name = "stuNo")int stuNo, Model model
    // stuService.deleteStu(stuVO.getStuNo);
    @GetMapping("/stuDelete")
    public String stuDelte(@RequestParam(name = "stuNo")int stuNo, Model model){
        //조회한 데이터 학생 삭제하기

        stuService.deleteStu(stuNo);
        return "redirect:/";
    }


}
