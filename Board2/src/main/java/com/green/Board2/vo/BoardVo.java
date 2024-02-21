package com.green.Board2.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BoardVo {
    private int boardNum;
    private String title;
    private String content;
    private String writer;
    private String createDate;
    private String readCnt;
}

