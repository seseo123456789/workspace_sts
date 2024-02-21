package com.green.StudentManager.service;

import com.green.StudentManager.vo.StuVO;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("stuService")
public class StuServiceImpl implements StuService {

    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public int insertStu(StuVO stuVO) {
        int result =sqlSession.insert("insertStu", stuVO);
        return result;
    }

    //학생목록조회
    @Override
    public List<StuVO> selectStuList() {
        return sqlSession.selectList("selectStuList");
    }
   //1명학생 정보조회
    @Override
    public StuVO selectStu(int stuNo) {
        return sqlSession.selectOne("selectStu", stuNo);
    }

    //1명 학생 정보 삭제
    @Override
    public int deleteStu(int stuNo) {
        return sqlSession.delete("deleteStu", stuNo);
    }


}
