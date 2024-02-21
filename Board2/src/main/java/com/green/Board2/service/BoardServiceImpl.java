package com.green.Board2.service;

import com.green.Board2.vo.BoardVo;
import com.green.Board2.vo.SearchVo;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("boardService")
public class BoardServiceImpl implements BoardService {

    @Autowired
    private SqlSessionTemplate sqlSession;

    // 글목록 조회 및 검색
    @Override
    public List<BoardVo> selectList(SearchVo searchVo) {
        return sqlSession.selectList("selectList",searchVo);
    }

    // 글저장
    @Override
    public int insertWrite(BoardVo boardVo) {
        return sqlSession.insert("boardMapper.insertWrite",boardVo);
    }

    // 글보기
    @Override
    public BoardVo boardDetail(int boardNum) {
        return sqlSession.selectOne("boardMapper.boardDetail", boardNum);
    }


    //글삭제
    @Override
    public void deleteBoard(int boardNum) {
        sqlSession.delete("boardMapper.deleteBoard",boardNum);
    }

    //글수정
    @Override
    public void updateBoard(BoardVo boardVo) {
     sqlSession.update("boardMapper.updateBoard", boardVo);
    }

    // 조회증가
    @Override
    public void updateReadCnt(int boardNum) {
        sqlSession.update("boardMapper.updateReadCnt",boardNum);
    }

    // 페이징 총데이터 갯수 조회
    @Override
    public int selectBoardCnt() {
        return sqlSession.selectOne("boardMapper.selectBoardCnt");
    }


}
