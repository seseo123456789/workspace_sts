package com.green.stu_java.service;

import com.green.stu_java.vo.JavaVO;
import org.apache.ibatis.annotations.Update;

import java.util.List;

public interface JavaService {

    //게시글 등록
   int boardList(JavaVO javaVO);

   //게시글 목록 조회
    List<JavaVO>selectBdList();

    //제목 클릭시 게시글 1개 정보보기
    JavaVO selectBoard(int boardNum);

    // 삭제 클릭시 게시글 1개 삭제
   int deleteBoard(int boardNum);

   //게시글 글 수정하기
  void updateBoard(JavaVO javaVO);




}
