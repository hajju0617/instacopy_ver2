package com.green.greengram.common;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Component      // 빈등록
@Getter
public class CustomFileUtils {
    @Value("${file.directory}")
    // 설정파일(yaml)에 file -> directory: 에 지정 되어있는 경로를 uploadPath에 주입
    private final String uploadPath;
    public CustomFileUtils(@Value("${file.directory}") String uploadPath) {
        // @Value("${file.directory}") ----> 객체 생성때 new CustomFileUtils("@Value("${file.directory}")")
        this.uploadPath = uploadPath;
    }

    // 폴더 만들기
    public String makeFolders(String path) {
        File folder = new File(uploadPath, path);   //File 클래스의 인스턴스 folder를 생성. 생성자의 인자로 uploadPath와 path를 받음
                                                    //이 인스턴스는 uploadPath 디렉토리 아래의 path 경로에 해당하는 파일 또는 디렉토리를 가리킵니다.
        folder.mkdirs();        // mkdirs() 메소드는 folder 객체가 가리키는 경로에 해당하는 디렉토리(들)를 생성
        return folder.getAbsolutePath();        // getAbsolutePath() : 절대 경로
    }

    // UUID 랜덤 파일
    public String makeRandomFileName() {
        return UUID.randomUUID().toString();        // UUID 랜덤 쓰는 이유 -> 중복을 피하려고
                                                    // 파일명 + 확장자가 겹치면 컴퓨터가 덮어써서
    }

    // 파일명에서 확장자 추출
    public String getExt(String fileName) {
        // "abcde.ddd.jpg"
        // a:0, b:1, c:2,...j:10, p:11, g:12
//        int idx = fileName.indexOf(".");   // indexOf는 왼쪽에서부터 찾았을때 . 이 있다면 양수값 없다면 음수값 (index는 0부터 시작)
//        int idx2 = fileName.lastIndexOf("."); // lastindexOf는 오른쪽에서
//        System.out.println("idx: " + idx);
//        System.out.println("idx2 : " + idx2);
        int idx = fileName.lastIndexOf(".");
        return fileName.substring(idx);     // substring : 매개변수로 숫자를 받아서 해당하는 숫자에서부터 끝까지 출력
    }

    // 랜덤 파일명 with 확장자 만들기
    public String makeRandomFileName(String fileName) {     //fileName에 원본 파일명이 들어옴
        // 랜덤 파일명.확장자 return
        return makeRandomFileName() + getExt(fileName);
    }

    // 랜덤 파일명 with 확장자 만들기 using MultipartFile
    public String makeRandomFileName(MultipartFile mf) {
        return mf == null ? null : makeRandomFileName(mf.getOriginalFilename());
        //makeRandomFileName(mf.getOriginalFilename()) --> 호출 public String makeRandomFileName(String fileName)
    }

    // 파일 저장 (target : 경로/파일명)
    public void transferTo(MultipartFile mf, String target) throws Exception {
        File savefile = new File(uploadPath, target); // 최종 경로
        // uploadPath = 파일이 저장되는 절대 경로

        mf.transferTo(savefile);

    }
}
