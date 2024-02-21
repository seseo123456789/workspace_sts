package com.green.DbTest.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
// STUDENT 테이블과 매칭되는 클래스
public class StudentVO {
        private int  stuNo;
        private String stuName;
        private int score;
        private String addr;

}
