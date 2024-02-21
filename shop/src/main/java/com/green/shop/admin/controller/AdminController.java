package com.green.shop.admin.controller;

import com.green.shop.admin.service.AdminServiceImpl;
import com.green.shop.admin.vo.SearchBuyVo;
import com.green.shop.buy.vo.BuyVo;
import com.green.shop.item.service.ItemServiceImpl;
import com.green.shop.item.vo.CategoryVo;
import com.green.shop.item.vo.ImgVo;
import com.green.shop.item.vo.ItemVo;
import com.green.shop.util.UploadUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Resource(name = "adminService")
    private AdminServiceImpl adminService;
    @Resource(name = "itemService")
    private ItemServiceImpl itemService;


    //상품등록 페이지 이동
    @GetMapping("/regItemForm")
    public String regItemForm(Model model, CategoryVo categoryVo) {
        List<CategoryVo> categoryList = itemService.selectCateGoryList();
         model.addAttribute("categoryList", categoryList);
        model.addAttribute("page", 2);

        return "content/admin/reg_item_form";
    }

    //상품등록 하기
    @PostMapping ("/regItem")
    public String regItem(ItemVo itemVo, HttpSession session
                            , @RequestParam(name ="imgMain")MultipartFile imgMain  // 첨부파일받는 매개변수
                            , @RequestParam(name="imgDetails")MultipartFile[] imgDetails){


    //-------------------- 상품 사진 파일 업로드------------------------
                //참고사항:  자바의 상수 는 대문자로쓰기로 암묵적 룰 final int NUM =10;

                //********** 이미지 업로드 방법************
                    // 1.업로드 경로(파일이 저장될 경로) + \\
                            //String uploadPath="D:\\01-STUDY\\dev\\workspace_sts\\shop\\src\\main\\resources\\static\\upload\\";
                            // 경로가 기니깐
                            // 클래스를 만들어서 메소드를 만들자! : util 패키지 > ConstantVariable 클래스
                    //2. 또 다른 클래스에 이미지 업로드 기능을 가진 메소드를 만들자!  : util 패키지 > UploadUtil 클래스

        // 메인 파일 이미지 업로드
            // (@) uploadFile() 메소드 에서 리턴되는 imgVo를 쓸수 있다 imgVo 안에는 원래파일명과 랜덤파일명,메인 들어있음
           ImgVo mainImgVo = UploadUtil.uploadFile(imgMain);

        // 상세 이미지들 업로드
           List<ImgVo> imgList = UploadUtil.multiUploadFile(imgDetails);

    //------------------------- 다음에 들어갈 Item_code 조회 -----------------------------
             int itemCode = adminService.selectNextItemCode();

    //------------------------- 상품  등록 -----------------------------
        itemVo.setItemCode(itemCode); //itemCode 내가 직접 지정하기
                //adminService.insertItem(itemVo); // 상품정보만 등록하는 실행문 주석처리함


    //------------------------- 상품 이미지 등록 -----------------------------
        // 상품 이미지 등록하려면 아이템코드, 원래파일명, 랜덤파일명, 메인파일인지 확인하기 필요!
        // 현재까지 itemVo 에는 상품명, 가격, 소개, 카테코드 정보가 들어옴 itemVo에 원래파일명, 랜덤파일명. 메인파일인지 확인하기 담음 (@가보세요)

        // 이미지 등록시 채워줘야하는 모든 데이터를 갖는 리스트 생성
        //List<ImgVo> imgList =new ArrayList<>(); // 이미지3장을 담을 통 /  1장당 imgVo
        // mainImgVo : 원래파일명, 랜덤파일명. 메인파일 담고있음 이제 아이템코드만 넣으면됨 아래 코드 확인!
        mainImgVo.setItemCode(itemCode);

        // 이제   mainImgVo 에 원래파일명, 랜덤파일명. 메인파일.아이템코드 들어가있음
        for( ImgVo img : imgList){
            img.setItemCode(itemCode);
        }
        imgList.add(mainImgVo);
        itemVo.setImgList(imgList);

        // 테스트
//        System.out.println(itemVo);
//        System.out.println("---------");
//        for(ImgVo e : imgList){
//            System.out.println(e);
//        }
        adminService.insertItem(itemVo); // itemVo


      return "redirect:/admin/regItemForm";
    }
//-------------------------------------------------------------------------------------------



    //  관리자 메뉴>구매이력 클릭시 구매 목록 조회 (관리자용)
        // searchBuyVo 커멘드객체(변수를 가지고 있는 클래스) 라서 html에 바로 쓸수 있다.
    @RequestMapping("/adminHistory")
    public String adminHistory(HttpSession session, Model model, SearchBuyVo searchBuyVo){
        System.out.println("!!!!!!!!!!!!!!!!!!" + searchBuyVo);

        //MemberVo loginInfo = (MemberVo) session.getAttribute("loginInfo");
        List<BuyVo> buyList=adminService.selectBuyList(searchBuyVo);
        model.addAttribute("buyList",buyList);

        model.addAttribute("page",1);

        return "content/admin/admin_history";
    }

    // 비동기 구매상세 내역 조회 (관리자용)
    @ResponseBody
    @PostMapping("/selectBuyDetail")
    public BuyVo selectBuyDetail(Model model, @RequestParam(name="buyCode")int buyCode){

        // 내가푼거
      //  List<BuyVo> buyDetailList =  adminService.selectBuyDetailList(buyVo.getBuyCode());
      //  for(BuyVo e : buyDetailList){
       //     System.out.println(e);
      //  }
          BuyVo buyVo =adminService.selectBuyDetail(buyCode);

        //model.addAttribute("buyDetailList",buyDetailList);
        return buyVo;
    }


    // 상품정보 클릭시  "상품목록조회" (관리자용)
    //@RequestParam(name = "itemCode") int itemCode 은 /updateItem에서 넘어오는 getItemCode();를 받기 위함임.
    // required = false  itemCode가 안들어올때
    // defaultValue  : 데이터가 없으면 기본값으로 설정하기 (html에서 넘어오는 기본자료형은 문자임. 그래서 "0"인데 자동으로 java 에서 숫자로 변경해줌)
    @GetMapping("/updateItemInfo")
    public String updateItemInfo(Model model, @RequestParam(name = "itemCode", required = false, defaultValue ="0" ) int itemCode){

        // 내가 푼거
        //List<ItemVo> itemInfo = adminService.selectItemInfo();
        //System.out.println(itemVo);
        //model.addAttribute("itemInfo",itemInfo);

        //ItemVo itemVo =adminService.selectItemInfo(itemCode);

        // 쌤푼거
        model.addAttribute("itemList",adminService.selectItemList());

        model.addAttribute("page",4);


        // (/updateItem)에서 넘어오는 getItemCode();를 받아서 html 로 넘기기
        model.addAttribute("updateItemCode", itemCode);

        return "content/admin/update_item";
    }

    // 비동기 , Map
    // 상품 기본 정보 조회
    @ResponseBody
    @PostMapping("/selectItemDetail")
    public Map<String, Object> updateItemInfoDetail(@RequestParam(name = "itemCode") int itemCode){

            // 상품 상세 조회
            ItemVo itemDetail =adminService.selectItemDetail(itemCode);
            System.out.println(itemDetail);
            // 카테고리 조회
            List<CategoryVo> cateList = itemService.selectCateGoryList();

    // 위 두 데이터를 담을 수 있는 Map 객체 생성
        Map<String, Object> map = new HashMap<>();
        map.put("itemDetail", itemDetail);
        map.put("cateList", cateList);

        return map;
    }

    //  상품기본정보 업데이트

        @PostMapping("/updateItem")
        public String updateItem(ItemVo itemVo){
            adminService.updateItem(itemVo);
            return "redirect:/admin/updateItemInfo?itemCode=" + itemVo.getItemCode();
        }










}
