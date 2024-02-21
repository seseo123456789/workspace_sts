package com.green.StudentManager.service;

import com.green.StudentManager.vo.StuVO;

import java.util.List;

public interface StuService {

    //SELECT 리턴 : 매번 바뀜.
    //INSERT, DELETE, UPDATE : void, int(정석)

    //리턴타입 : 쿼리 실행 결과를 받아옴
    // INSERT, DELETE, UPDATE는 쿼리 실행 결과가 몇개의 행이 삽입, 삭제, 수정 되었는지 보여줌.
    // 그래서 위 세개의 쿼리 결과 리턴 타입은 무조건 int, void
    // select 는 어떤 쿼리인지에 따라 리턴타입이 달라짐.
    // 조회 결과 데이터가 0행 혹은 1행 일때: vo클래스
    // 조회 결과 데이터 행의 개수가 매번 다르다: List<VO>

    //매개변수 : 쿼리 실행 시 빈값을 채우는 역할
    // 빈 값을 채울 데이터가 0개,1개, 여러개 구분.
    // 빈 값 0개 : 매개변수 필요없음.
    // 빈 값 1개 :
                // 1)빈값의 자료형이 숫자 인 경우 / 매개변수로 int 자료형 하나
                // 2) 빈값의 자료형이 문자열 인 경우 / 매개변수로 String 자료형 하나
    // 빈 값이 여러개인 경우는 vo 객체

    //학생등록
    int insertStu(StuVO stuVO);

    //학생들 목록 조회
    List<StuVO> selectStuList();

    //1명학생 상세정보 조회
    StuVO selectStu(int stuNo);

    //학생 1명 정보 삭제
    int deleteStu(int stuNo);
}
