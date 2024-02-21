package com.green.Board2.service;


import com.green.Board2.vo.ReplyVo;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("replyService")
public class ReplyServiceImpl implements ReplyService {

    @Autowired
    private SqlSessionTemplate sqlSession;

    // 댓글 등록
    @Override
    public void insertReply(ReplyVo replyVo) {
         sqlSession.insert("replyMapper.insertReply", replyVo);
    }

    @Override
    public List<ReplyVo> detailReply(int boardNum) {
        return sqlSession.selectList("replyMapper.detailReply", boardNum);
    }

    //댓글 삭제
    @Override
    public void deleteReply(int replyNum) {
        sqlSession.delete("replyMapper.deleteReply", replyNum);
    }


}
