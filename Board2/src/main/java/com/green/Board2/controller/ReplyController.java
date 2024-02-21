package com.green.Board2.controller;

import com.green.Board2.service.ReplyServiceImpl;
import com.green.Board2.vo.MemberVo;
import com.green.Board2.vo.ReplyVo;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/reply")
public class ReplyController {

    @Resource(name = "replyService")
    private ReplyServiceImpl replyService;


        // 댓글 저장
        @PostMapping ("/replyInsert")
            public String replyInsert(ReplyVo replyVo, HttpSession session){
            //로그인 정보가져오기
            MemberVo loginInfo =(MemberVo)session.getAttribute("loginInfo");
            replyVo.setWriter(loginInfo.getMemberId());
            //댓글저장
            replyService.insertReply(replyVo);
             return "redirect:/board/boardDetail?boardNum="+replyVo.getBoardNum();
            }


    //댓글삭제
        @GetMapping("/replyDelete")
            public String replyDelete(@RequestParam(name = "replyNum") int replyNum, @RequestParam(name= "boardNum") int boardNum){
            replyService.deleteReply(replyNum);
            return "redirect:/board/boardDetail?boardNum="+boardNum;
        }




}
