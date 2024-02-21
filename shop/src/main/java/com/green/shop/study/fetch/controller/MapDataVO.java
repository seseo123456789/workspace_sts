package com.green.shop.study.fetch.controller;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class MapDataVO {
    private int cartCode;
    private MapDatMemberVO memberInfo;
    private itemInfoVO itemInfo;

}

        @Getter
        @Setter
        @ToString
        class MapDatMemberVO{
            private String memberId;
            private String memberName;
        }


        @Getter
        @Setter
        @ToString
        class itemInfoVO{
            private int itemCode;
            private String itemName;
            private int itemPrice;
            private List<imgInfoVO> imgList;
        }


        @Getter
        @Setter
        @ToString
        class imgInfoVO{
            private String imgName;
            private int imgCode;
        }