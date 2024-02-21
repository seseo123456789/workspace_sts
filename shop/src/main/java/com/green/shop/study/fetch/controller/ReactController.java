package com.green.shop.study.fetch.controller;

import com.green.shop.item.service.ItemService;
import com.green.shop.item.service.ItemServiceImpl;
import com.green.shop.item.vo.CategoryVo;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

// 리엑트 맛보기
@RestController
@RequestMapping("/rest")
public class ReactController {

        @Resource(name = "itemService")
    private ItemServiceImpl itemService;

    @GetMapping("/test1")
    public String test1(){
        return "test.html";
    }

    @GetMapping("/test2")
    public List<CategoryVo> test2(){

        return itemService.selectCateGoryList();
    }


}
