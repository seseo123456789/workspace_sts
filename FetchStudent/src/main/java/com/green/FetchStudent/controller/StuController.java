package com.green.FetchStudent.controller;

import com.green.FetchStudent.service.StuService;
import com.green.FetchStudent.service.StuServiceImpl;
import com.green.FetchStudent.vo.ClassVo;
import com.green.FetchStudent.vo.DetailVo;
import com.green.FetchStudent.vo.ScoreVo;
import com.green.FetchStudent.vo.StuVo;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class StuController {
    @Resource(name = "stuService")
    private StuServiceImpl stuService;

    @RequestMapping("/")
    public String first(Model model, StuVo stuVo ){
        //  학급 목록 데이터 조회
        List<ClassVo> classList =stuService.selectClassList();
        model.addAttribute("classList",classList);

        //동기통신 방식: 학생 목록 데이터 조회
        List<StuVo> stuList =stuService.selectStuList(stuVo);
        model.addAttribute("stuList",stuList);

        return "stu_manage";
    }
    //비동기방식: 학급 선택시 학생정보 목록조회
    @ResponseBody
    @PostMapping("/fetchSelect")
    public List<StuVo> fetchSelect(StuVo stuVo){
        List<StuVo> stuList =stuService.selectStuList(stuVo);
        return stuList; //html javascript로 데이터 던져주기
    }

    @ResponseBody
    @PostMapping("/stuDetail")
    public DetailVo stuDetail(@RequestParam(name = "stuNum") int stuNum){

        // 클릭한 학생의 상세 정보 조회
        StuVo stuInfo =stuService.detailStu(stuNum);

        // 클릭한 학생의 점수 정보 조회
        ScoreVo scoreInfo =stuService.selectScoreInfo(stuNum);

        //DetailVo 에 StuVo 와 ScoreVo 넣기
        DetailVo result = new DetailVo();

        result.setStuVo(stuInfo);
        result.setScoreVo(scoreInfo);

        // 조회한 데이터를 가지고 자바스크립트로 이동
        return result;
    }
    @ResponseBody
    @PostMapping("/insertScore")
    public void insertScore(ScoreVo scoreVo){
        // 점수 저장 하기
        stuService.insertScore(scoreVo);
    }

}

