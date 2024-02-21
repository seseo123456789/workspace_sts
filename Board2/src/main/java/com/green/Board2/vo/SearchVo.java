package com.green.Board2.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString

public class SearchVo extends PageVo {
    private String searchType;
    private String searchValue;
}
