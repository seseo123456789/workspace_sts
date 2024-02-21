package com.green.Board2.service;

import com.green.Board2.vo.BoardVo;
import com.green.Board2.vo.SearchVo;

import java.util.List;

public interface BoardService {

    //게시글 조회
    List<BoardVo>selectList(SearchVo searchVo);

    //글 저장
    int insertWrite(BoardVo boardVo);

    // 글 상세보기
    BoardVo boardDetail(int boardNum);

    // 글 삭제
    void deleteBoard(int boardNum);

    // 글 수정
    void updateBoard(BoardVo boardVo);

    // 조회수 증가
    void updateReadCnt(int boardNum);

    //총데이터갯수 세기
    int selectBoardCnt();



}
