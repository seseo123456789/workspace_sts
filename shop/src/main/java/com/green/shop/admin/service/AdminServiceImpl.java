package com.green.shop.admin.service;

import com.green.shop.admin.vo.SearchBuyVo;
import com.green.shop.buy.vo.BuyVo;
import com.green.shop.item.vo.ItemVo;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

    @Autowired
    private SqlSessionTemplate sqlSession;

    // 상품정보등록하기
    @Override
    @Transactional(rollbackFor = Exception.class)
            // @Transactional : 메소드 내부의 쿼리 실행을 정상적으로 모두실행시 성공!  쿼리 중 하나라서 실패시 롤백!
            // (rollbackFor = Exception.class)  언제든 어떤일이 일어나면 실패로 간주해서 무조건 롤백!
    public void insertItem(ItemVo itemVo) {
        sqlSession.insert("adminMapper.insertItem", itemVo);
        // 상품이미지 등록하기
        sqlSession.insert("adminMapper.insertImgs", itemVo);
    }

    // 상품이미지 등록하기 를 위에 상품정보등록 메소드에 합치기
//    @Override
//    public void insertImgs(ItemVo itemVo) {
//        sqlSession.insert("adminMapper.insertImgs", itemVo);
//
//    }



    // 다음에 들어갈 ItemCode 조회
    @Override
    public int selectNextItemCode() {
        return sqlSession.selectOne("adminMapper.selectNextItemCode");
    }


    //  구매이력내역 목록 조회 (관리자용)
    @Override
    public List<BuyVo> selectBuyList(SearchBuyVo searchBuyVo) {
        return sqlSession.selectList("adminMapper.selectBuyList", searchBuyVo);
    }

    //  구매이력 상세내역 목록 조회 (관리자용)
    @Override
    public List<BuyVo> selectBuyDetailList(int buyCode) {
        return sqlSession.selectList("adminMapper.selectBuyDetailList", buyCode);
    }

    @Override
    public BuyVo selectBuyDetail(int buyCode) {
        return sqlSession.selectOne("adminMapper.selectBuyDetail", buyCode);
    }

    @Override
    public List<ItemVo> selectItemInfo() {
        return sqlSession.selectList("adminMapper.selectItemInfo");
    }

    @Override
    public ItemVo selectItemInfoDetail(int itemCode) {
        return sqlSession.selectOne("adminMapper.selectItemInfoDetail", itemCode);
    }

    //  상품 정보 변경 화면에서 상품 목록 조회  쌤풀이
    @Override
    public List<ItemVo> selectItemList() {
        return sqlSession.selectList("adminMapper.selectItemList");
    }
    //  상품 정보 변경  쌤풀이
    @Override
    public ItemVo selectItemDetail(int itemCode) {
        return sqlSession.selectOne("adminMapper.selectItemDetail", itemCode);
    }

    // 상품기본정보 업데이트
    @Override
    public void updateItem(ItemVo itemVo) {
        sqlSession.update("adminMapper.updateItem", itemVo);
    }
}
