package com.green.Board2.controller;

import com.green.Board2.service.BoardService;
import com.green.Board2.service.BoardServiceImpl;
import com.green.Board2.service.ReplyServiceImpl;
import com.green.Board2.vo.BoardVo;
import com.green.Board2.vo.MemberVo;
import com.green.Board2.vo.ReplyVo;
import com.green.Board2.vo.SearchVo;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import lombok.Getter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/board")
public class BoardController {

        @Resource(name = "boardService")
        private BoardServiceImpl boardService;
        @Resource(name = "replyService")
        private ReplyServiceImpl replyService;

    // 목록 조회, 글쓰기, 상세조회, 수정, 삭제

    //게시글 목록 페이지로 가기
    @RequestMapping("/list")
    public String boardList(Model model, SearchVo searchVo){

        //전체 데이터 갯수 조회 (구하기)
        int totalDateCnt= boardService.selectBoardCnt();
        searchVo.setTotalDataCnt(totalDateCnt);

        //page 정보 (페이징)
        searchVo.setPageInfo();
        
        List<BoardVo> list= boardService.selectList(searchVo);
        model.addAttribute("list",list);
        // (위코드랑 같다): model.addAttribute("list",boardService.selectList());

        return "list";
    }
    // 글쓰기 페이지로 가기
    @GetMapping("/goWrite")
    public String goWrite(){
        return "write";
    }

    // 글쓰고 저장해서 목록으로 가기
    @PostMapping("/write")
    public String write(BoardVo boardVo, HttpSession session){
        //세션에 데이터 저장하기 loginInfo: 아이디, 이름, 관리자 들어가있음
        MemberVo loginInfo =(MemberVo) session.getAttribute("loginInfo");

        //boardVo에 작성자 데이터 저장
        boardVo.setWriter(loginInfo.getMemberId());
        boardService.insertWrite(boardVo);
        return "redirect:/board/list";
    }
    // 글 상세보기 + 조회수 증가
    @GetMapping("/boardDetail")
    public String boardDetail(@RequestParam(name = "boardNum") int boardNum, Model model){
        //조회수 증가
        boardService.updateReadCnt(boardNum);
        //상세보기
        BoardVo board=boardService.boardDetail(boardNum);
        model.addAttribute("board",board);

        //댓글보기
        List<ReplyVo> detailReply =replyService.detailReply(boardNum);
        model.addAttribute("detailReply",detailReply);

        return "detailBoard";
    }
    //글 삭제
    @GetMapping("/goDelete")
   public String boardDelete(BoardVo boardVo){
      boardService.deleteBoard(boardVo.getBoardNum());

       return "redirect:/board/list";
   }

    //상세내용을 가지고 글 수정 페이지로 가기
    @GetMapping("boardUpdate")
   public String boardUpdate(BoardVo boardVo ,Model model){
       BoardVo boardVo1=boardService.boardDetail(boardVo.getBoardNum());
       model.addAttribute("boardVo1",boardVo1);
        return "update_form";
   }

   //글 1개 수정하기
    @PostMapping("writeUpdate")
    public String writeUpdate(BoardVo boardVo){
        boardService.updateBoard(boardVo);
        return "redirect:/board/list";
    }

    // 검색


}
