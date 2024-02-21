package com.green.Board2.service;

import com.green.Board2.vo.ReplyVo;

import java.util.List;

public interface ReplyService {

    //댓글 등록
    void insertReply(ReplyVo replyVo);

    // 댓글 상세보기
    List<ReplyVo> detailReply(int boardNum);

    //댓글 삭제
    void deleteReply(int replyNum);
}
