package com.green.FetchStudent.service;


import com.green.FetchStudent.vo.ClassVo;
import com.green.FetchStudent.vo.ScoreVo;
import com.green.FetchStudent.vo.StuVo;

import java.util.List;

public interface StuService  {

    //학생 목록조회
    List<StuVo> selectStuList(StuVo stuVo);

    //학급 목록조회
    List<ClassVo>selectClassList();

    // 학생 상세 조회
    StuVo detailStu(int stuNum);

    // 학생 정보 조회
    ScoreVo selectScoreInfo(int stuNum);

    // 점수저장
    void insertScore(ScoreVo scoreVo);

}
