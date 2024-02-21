package com.green.Board2.service;


import com.green.Board2.vo.MemberVo;

public interface MemberService {

    // 회원가입 하기
   void memberReg(MemberVo memberVo);

   //로그인하기
    MemberVo login(MemberVo memberVo);

}
