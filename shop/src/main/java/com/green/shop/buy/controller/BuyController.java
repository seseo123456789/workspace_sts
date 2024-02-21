package com.green.shop.buy.controller;

import com.green.shop.buy.serviece.BuyService;
import com.green.shop.buy.serviece.BuyServiceImpl;
import com.green.shop.buy.vo.BuyDetailVo;
import com.green.shop.buy.vo.BuyVo;
import com.green.shop.cart.service.CartServiceImpl;
import com.green.shop.cart.vo.CartViewVo;
import com.green.shop.cart.vo.CartVo;
import com.green.shop.member.vo.MemberVo;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/buy")
public class BuyController {

    @Resource(name = "buyService")
    private BuyServiceImpl buyService;

    @Resource(name= "cartService")
    private CartServiceImpl cartService;



    //장바구니에 담긴 목록 조회 하기
           @GetMapping("/buyCarts")
           // cartvo 에  cartCode 가 들어가 있음
            public String buyCarts(CartVo cartVo, HttpSession session){

         //    ***** 상세 정보 테이블에 들어갈 데이터 넣기 ITEM_CODE, BUY_CNT, TOTAL_PRICE (BUY_DETAIL_CODE 제외)
               // 구매 상세 테이블에 insert 할 item_code, buy_cnt, total_price 조회

               List<CartViewVo> cartViewList = cartService.selectCartListForBuy(cartVo);

// -----------------------------------------------------------------------------------
        //    *****구매 정보 테이블에 들어갈 데이터 넣기 BUY_CODE, MEMBER_ID, BUY_PRICE (BUY_DATE 제외)

               // 구매 정보 테이블에 들어갈 buy_price 상품들구매총가격
               int buyPrice=0;
               for( CartViewVo e : cartViewList){
                   buyPrice = buyPrice + e.getTotalPrice();
               }
               //  구매 정보 테이블에 들어갈 구매자 ID 가져오기
               MemberVo loginInfo =(MemberVo) session.getAttribute("loginInfo");
               String memberId= loginInfo.getMemberId();
// ---------------------------------------------------------------------------------

               // ***** 구매 정보 테이블 &  상세 테이블에  다음에 들어갈 BUY_CODE 결정
               int buyCode = buyService.selectNextBuyCode();

// ------------//********* insert 하기-------------------------------------------------------------

               BuyVo buyVo1 = new BuyVo();
             
               // buyNo1 에  buyCode 넣기
               buyVo1.setBuyCode(buyCode);
               // buyNo1 에  memberId 넣기
               buyVo1.setMemberId(memberId);
               // buyNo1 에  buyPrice 넣기
               buyVo1.setBuyPrice(buyPrice);
               
              // buyService.insertBuys(buyVo1); // 위에 저장한 자료를 모두 저장하는 기능 실행

                    List<BuyDetailVo> buyDetailVoList = new ArrayList<>();
                for( CartViewVo viewVo : cartViewList ){
                   BuyDetailVo DetailVo = new BuyDetailVo();
                        DetailVo.setItemCode(viewVo.getItemCode());
                        DetailVo.setBuyCnt(viewVo.getCartCnt());
                        DetailVo.setTotalPrice(viewVo.getTotalPrice());

                       buyDetailVoList.add(DetailVo);
               }
                buyVo1.setBuyDetailList(buyDetailVoList);

                System.out.println(buyVo1);


               buyService.insertBuys(buyVo1, cartVo);
               return "redirect:/cart/list";
            }
            
            
            //  상품상세페이지 에서 바로구매 하기
            @PostMapping ("/buyDirect")
            public String buyDirect(BuyVo buyVo, HttpSession session, BuyDetailVo buyDetailVo){

               // 다음에 들어가야 하는 buy_code 값을 조회
                int buyCode = buyService.selectNextBuyCode();
               // MEMBER_ID 조회
               MemberVo loginInfo = (MemberVo)session.getAttribute("loginInfo");
                String memberId = loginInfo.getMemberId();



                // buyVo 에 조회한 데이터 저장하기
                buyVo.setBuyCode(buyCode);
                buyVo.setMemberId(loginInfo.getMemberId());
                buyVo.setBuyPrice(buyDetailVo.getTotalPrice());
                buyDetailVo.setBuyCode(buyCode);


                buyService.insertBuyDirect(buyVo, buyDetailVo);
              return "redirect:/";
           }

           // 구매 이력 페이지
            // @RequestParam(name="page", required=false, defaultValue="history") String page
           @GetMapping("/history")
            public String history(HttpSession session, Model model, @RequestParam(name="page", required=false, defaultValue="history") String page){
               //String page= "history";
               model.addAttribute("page", page);

               // 아이디 호출
               MemberVo loginInfo =(MemberVo)session.getAttribute("loginInfo");
               //구매목록 조회 (아이디 가져오면 다른 정보도 함께 호출됨)
               List<BuyVo> buyList = buyService.selecetBuyList(loginInfo.getMemberId());
                for(BuyVo e : buyList){
                    System.out.println(e);} //  출력해서 BuyVo 내용물 확인하기
               model.addAttribute("buyList",buyList);
               return "/content/buy/history";
           }

}

