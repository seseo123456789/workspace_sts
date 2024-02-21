package com.green.shop.admin.vo;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SearchBuyVo {
    private String searchBuyType;
    private String searchValue;
    private String fromDate;
    private String toDate;
}
