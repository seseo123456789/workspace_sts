package com.green.stu_java.service;

import com.green.stu_java.vo.JavaVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

//JavaServiceImpl javaService = new JavaServiceImpl();
@Service("javaService")
public class JavaServiceImpl implements JavaService {

    @Autowired
    private SqlSessionTemplate sqlSession;



    @Override
    public int boardList(JavaVO javaVO) {
      int result = sqlSession.insert("boardList", javaVO);
        return result;
    }
//게시글 조회
    @Override
    public List<JavaVO> selectBdList() {

        return sqlSession.selectList("selectBdList");
    }

    //게시글 상세 데이터 기능
    @Override
    public JavaVO selectBoard(int boardNum) {
        return sqlSession.selectOne("selectBoard", boardNum);
    }
    //게시글 삭제 기능
    @Override
    public int deleteBoard(int boardNum) {
        return sqlSession.delete("deleteBoard", boardNum);
    }
    //게시글 수정 기능
    @Override
    public void updateBoard(JavaVO javaVO) {
         sqlSession.update("boardMapper.updateBoard", javaVO);
    }


}
