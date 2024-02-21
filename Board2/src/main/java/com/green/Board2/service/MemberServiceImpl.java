package com.green.Board2.service;

import com.green.Board2.vo.MemberVo;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("memberService")
public class MemberServiceImpl implements MemberService{

    @Autowired
    private SqlSessionTemplate sqlSession;


    @Override
    public void memberReg(MemberVo memberVo) {
        sqlSession.insert("memberMapper.memberReg", memberVo);
    }

    @Override
    public MemberVo login(MemberVo memberVo) {
        return sqlSession.selectOne("memberMapper.login", memberVo);
    }
}
