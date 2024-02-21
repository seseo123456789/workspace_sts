package com.green.shop.admin.service;

import com.green.shop.admin.vo.SearchBuyVo;
import com.green.shop.buy.vo.BuyVo;
import com.green.shop.item.vo.ItemVo;

import java.util.List;

public interface AdminService {

    // 상품등록 + 상품이미지 등록
    void insertItem(ItemVo itemVo);

        // 상품 이미지 등록을 위에 상품등록에 합치기
        //void insertImgs(ItemVo itemVo);

    // 다음에 들어갈 ItemCode 조회
    int selectNextItemCode();

    //  구매이력내역 목록 조회 (관리자용)
    List<BuyVo> selectBuyList(SearchBuyVo searchBuyVo);

    //  구매이력 상세내역 목록 조회 (관리자용) 내가 푼거
    List<BuyVo> selectBuyDetailList(int buyCode);

    //구매이력 상세내역 목록 조회 (관리자용) 쌤 풀이
    BuyVo selectBuyDetail(int buyCode);


    //상품정보변경 내가푼거
    List<ItemVo> selectItemInfo();
    ItemVo selectItemInfoDetail(int itemCode);




    // 상품 정보 변경 화면에서 상품 목록 조회   //상품정보변경 쌤풀이
    List<ItemVo> selectItemList();
    ItemVo selectItemDetail(int itemCode);


    // 기본정보 변경
    void updateItem(ItemVo itemVo);


}
