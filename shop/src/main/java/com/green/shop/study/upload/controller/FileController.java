package com.green.shop.study.upload.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

//파일 업로드, 다운로드 학습용 컨트롤러
@Controller
@RequestMapping("/file")
public class FileController {

    //파일 첨부 페이지로 이동
    @GetMapping("/uploadForm")
    public String uploadForm(){

        //파일 확장자 추출
        String file1= "abc.jpg";
        System.out.println(file1.substring(1, 5));

        //String file1= "abc.jpg";
        // 1: 두번째문자인 'b', 5: 5번째 이전까지의 문자 / 출력: bc.j

        System.out.println(file1.indexOf(".")); // 세번째문자인 '.' 을 실행하면 출력: 3
        System.out.println(file1.lastIndexOf(".")); // 문자가 '.' 중복으로 있을경우 실행하면 출력: 5 "abc.j.jpg"
        System.out.println("------------");
        System.out.println(file1.substring(file1.lastIndexOf(".")));

        return "test/upload/upload_form";
    }

    //파일 업로드하기(그냥 암기하세요)
    @PostMapping("/upload")
    public String upload(@RequestParam(name="img1") MultipartFile img1
                         , @RequestParam(name="img2") MultipartFile img2){
        // -------------- img1 업로드 -----------------
        // 파일선택 : 첨부한 파일 명을 getOriginalFilename()으로 받을수 있다.
       String originalFilename = img1.getOriginalFilename();
       System.out.println(originalFilename); //콘솔출력: 파일명

       //업로드 경로(파일이 저장될 경로) + \\
        String uploadPath="D:\\01-STUDY\\dev\\workspace_sts\\shop\\src\\main\\resources\\static\\upload\\";

        // 업로드할 경로 및 파일명을 하나의 문자열로 나열
        String fileName = uploadPath + originalFilename; // 경로 + 파일명

        // 파일 업로드
        //java.io.File
        // 파일 업로드 코드 작성
        //  File file = new File(fileName);
        //  img1.transferTo(file); 파일 업로드 코드

        // try (파일업로드실행)실행되다가 오류가 발생할때 catch 발생
        try {
            File file = new File(fileName);
            img1.transferTo(file);
        } catch (IOException e) {
            System.out.println("!!!파일 첨부 중 오류 발생!!");
            throw new RuntimeException(e);
        }

        // 참고사항: 특수문자를 문자열로 취급하기 위해 특수문자 앞에 \를 붙여주면 됨
         String space = "\"안녕\""; //콘솔출력: "안녕"


        // -------------- img2 업로드 랜덤이름할때 -----------------



        //서버에 저장할 파일명 생성
        //원본 파일의 확장자만 주출
        //"abc.jpg"
        //"abcd.jpg"
        //"abcde.jpg"
        // 서버에 저장할 파일명 생성 ( extension: 확장자  (a.jpg, a.hwp,a.xlsx, a.png) )

         String  originalFileName2 =img2.getOriginalFilename();
         String extension = originalFileName2.substring(originalFileName2.lastIndexOf("."));
         System.out.println("===============");


         //서버에 저장할 파일명 생성 / (그냥 암기하세요) /랜덤으로 파일명 줌./ 중복없음
         String uuid= UUID.randomUUID().toString();
         String attachedFileName = uuid + extension; //uuid + 확장자

        try {
            File file1= new File(uploadPath + attachedFileName);
            img2.transferTo(file1);
        } catch (IOException e) {
            System.out.println("파일 첨부 중 오류 발생~");
            e.printStackTrace(); // 오류가 뜨면 몇번째 오류가 발생하는지 알려주는 코드
        }

        return "redirect:/file/uploadForm";
    }

    // 다중 첨부 페이지로 이동 (파일2개 한번에 업로드)
        @GetMapping("/multiForm")
        public String multiForm(){
            return "test/upload/multi_form";
        }

        @PostMapping("/multi")
        public String multi(@RequestParam(name = "imgs") MultipartFile[] imgs){

            //업로드 경로(파일이 저장될 경로) + \\
            String uploadPath="D:\\01-STUDY\\dev\\workspace_sts\\shop\\src\\main\\resources\\static\\upload\\";

            // 파일들 선택하기 // 업로드할 파일이 여러개라서 반복문 적용
            for(MultipartFile img :imgs){
                System.out.println("***********");
                System.out.println(img.getOriginalFilename());

                // 확장자 추출
               String extension = img.getOriginalFilename().substring(img.getOriginalFilename().lastIndexOf("."));
               // 첨부될 파일명 // 랜덤으로 파일명이 변경되서 저장됨
                String attachedFileName =UUID.randomUUID().toString() + extension;

                // 첨부
                try {
                    File file =new File(uploadPath + attachedFileName); //경로 + 파일명
                    img.transferTo(file); // 실질적으로 파일업로드하는 코드 그냥 암기
                }
                catch (IOException e){
                    System.out.println("다중 첨부 중 오류 발생합니당당당");
                    e.printStackTrace();
                }
            }


            return "redirect:/file/multiForm";
        }

}
