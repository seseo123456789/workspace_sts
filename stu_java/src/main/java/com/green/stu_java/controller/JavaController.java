package com.green.stu_java.controller;

import com.green.stu_java.service.JavaService;
import com.green.stu_java.service.JavaServiceImpl;
import com.green.stu_java.vo.JavaVO;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.naming.Name;
import java.util.List;

@Controller
public class JavaController {
    //JavaServiceImpl javaService1 = new JavaServiceImpl();

    @Resource(name = "javaService")
    private JavaServiceImpl javaService;


    //게시글 목록화면
    @GetMapping("/br")
    public String boardList(Model model){
        //게시글 목록조회
        List<JavaVO> list = javaService.selectBdList();
        // 조회한 목록을 html로  전달
        model.addAttribute("boardList", list);
        return "board_list";
    }

    @GetMapping("/goBoard")
    public String goBoard(){
        return "board_write_form";
    }

    @PostMapping("/boardWrite")
    public String boardWrite(JavaVO javaVO){
        // 글등록
        javaService.boardList(javaVO);
        return "redirect:/br";
    }

    //제목 클릭시 글조회
    @GetMapping("/boardDetail")
    public String boardDetail(@RequestParam(name="boardNum") int boardNum, Model model){
        JavaVO board=javaService.selectBoard(boardNum);
        model.addAttribute("boardInfo",board);

        return "board_detail";
    }
    // 글 1개 삭제
    @GetMapping("boardDelete")
    public String boardDelete(@RequestParam(name="boardNum") int boardNum){
        javaService.deleteBoard(boardNum);

        return "redirect:/br";
    }

    //글 수정 페이지로 가기
    @GetMapping("boardUpdate")
    public String boardUpdate(JavaVO javaVO, Model model){
        //수정하고자 하는 게시글의 데이터 조회 + html 전달
    JavaVO view= javaService.selectBoard(javaVO.getBoardNum());
    model.addAttribute("view", view);
        return "update_form";
    }

    // 글 1개 수정
    @PostMapping("update")
    public String update(JavaVO javaVO){
        javaService.updateBoard(javaVO);

        return "redirect:/boardDetail?boardNum=" + javaVO.getBoardNum();
    }


}






