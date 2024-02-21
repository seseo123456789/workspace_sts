package com.green.FetchStudent.service;


import com.green.FetchStudent.vo.ClassVo;
import com.green.FetchStudent.vo.ScoreVo;
import com.green.FetchStudent.vo.StuVo;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("stuService")
public class StuServiceImpl implements StuService{

    @Autowired
    private SqlSessionTemplate sqlSession;

    @Override
    public List<StuVo> selectStuList(StuVo stuVo) {
        return sqlSession.selectList("stuMapper.selectStuList", stuVo);
    }

    @Override
    public List<ClassVo> selectClassList(){
        return sqlSession.selectList("stuMapper.selectClassList");
    }

    @Override
    public StuVo detailStu(int stuNum) {
        return sqlSession.selectOne("stuMapper.detailStu",stuNum);
    }

    @Override
    public ScoreVo selectScoreInfo(int stuNum) {
        return sqlSession.selectOne("stuMapper.selectScoreInfo",stuNum);
    }

    @Override
    public void insertScore(ScoreVo scoreVo) {
        sqlSession.insert("stuMapper.insertScore",scoreVo);
    }


}
