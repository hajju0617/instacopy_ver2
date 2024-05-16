package com.green.greengram.common;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Component      // 빈등록

/*
@Component는 Spring 프레임워크에서 사용되는 어노테이션 중 하나 (클래스를 Spring Bean 으로 등록하기 위해 사용 됨)
Spring Bean은 Spring 프레임워크에서 관리되는 객체로, 애플리케이션의 구성 요소로 사용 됨
@Component가 붙은 클래스는 Spring 컨테이너에 의해 자동으로 등록되며, 이후에는 다른 빈 객체와 상호작용하거나, Spring의 서비스를 이용할 수 있음.

1. 자동 등록: @Component가 붙은 클래스는 Spring 컨테이너에 의해 자동으로 등록됨.
2. 다양한 타입의 빈 등록 가능: @Component는 클래스뿐만 아니라 인터페이스, enum 등 다양한 타입의 빈을 등록할 수 있음
3. 빈 이름 지정 가능: @Component 어노테이션에 value 속성을 사용하여 빈의 이름을 지정할 수 있음.
   지정하지 않을 경우 클래스의 이름을 기반으로 빈의 이름이 자동으로 생성됩니다.
4. 다른 어노테이션과 함께 사용 가능: @Component는 다른 어노테이션과 함께 사용할 수 있습니다
 */
@Getter
public class CustomFileUtils {
    @Value("${file.directory}")
    // 설정파일(yaml)에 file -> directory: 에 지정 되어있는 경로를 uploadPath에 주입
    private String uploadPath;
    public CustomFileUtils(@Value("${file.directory}") String uploadPath) {
        // @Value("${file.directory}") ----> 객체 생성때 new CustomFileUtils("@Value("${file.directory}")")
        this.uploadPath = uploadPath;
    }

    // 폴더 만들기
    // 이 코드는 특정 경로에 폴더(디렉토리)를 생성하는 메서드
    public String makeFolders(String path) {        // path는 생성하고자 하는 폴더의 경로를 나타냅니다. (path는 메서드 호출 시 제공된 상대 경로)
        File folder = new File(uploadPath, path);   //File 클래스의 인스턴스 folder를 생성. 생성자의 인자로 uploadPath와 path를 받음
                                                    //결과적으로, 이 folder 객체는 uploadPath 아래 path에 해당하는 파일 또는 디렉토리를 가리키게 됩니다

        folder.mkdirs();        // mkdirs() 메소드는 folder 객체가 가리키는 경로에 해당하는 디렉토리(들)를 생성
        // mkdirs() 메서드는 folder 객체가 가리키는 경로에 해당하는 디렉토리를 생성.
        // 만약 경로에 여러 단계의 디렉토리가 필요한 경우, mkdirs() 메서드는 필요한 모든 상위 디렉토리를 포함하여 해당 경로에 모든 디렉토리를 생성합니다
        // 예를 들어, path가 "a/b/c"라면, "a", "a/b", "a/b/c" 모든 디렉토리를 생성합니다.

        return folder.getAbsolutePath();        // getAbsolutePath() : 절대 경로
        // getAbsolutePath() 메서드는 folder 객체의 절대 경로를 반환 함
        // folder 객체가 가리키는 디렉토리의 전체 경로를 나타냅니다
        // makeFolders 메서드는 이 절대 경로를 문자열 형태로 반환

        //절대 경로(Absolute Path): 절대 경로는 파일이나 폴더의 전체 경로를 나타내며
        //루트 디렉토리로부터 시작하여 해당 파일 또는 폴더까지의 모든 경로를 포함합니다.
        //상대 경로(Relative Path): 상대 경로는 현재 작업 중인 디렉토리를 기준으로 경로를 지정합니다.
        // 즉, 파일이나 폴더의 위치를 현재 위치로부터 상대적으로 나타냅니다.
    }
    // 자바의 File 클래스를 사용하여 지정된 경로에 폴더를 생성하고, 생성된 폴더의 절대 경로를 반환합니다

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
        /*
        MultipartFile 객체를 매개변수로 받아, 그 파일의 원본 이름을 기반으로 랜덤 파일 이름을 생성하는 메서드
        MultipartFile은 주로 스프링 프레임워크의 웹 애플리케이션에서 파일 업로드를 다룰 때 사용되는 인터페이스
        사용자가 웹 폼을 통해 파일을 업로드할 때 그 파일 데이터는 MultipartFile 객체로 전달됨
        이 객체를 통해 파일의 내용, 이름, 타입 등 다양한 정보를 얻을 수 있습니다

        public String makeRandomFileName(MultipartFile mf)
        이 메서드는 MultipartFile 타입의 매개변수 mf를 받아들이고, String 타입의 값을 반환합니다.
        이 메서드의 목적은 mf에서 파일 이름을 추출하여 그 이름을 기반으로 한 랜덤 파일 이름을 생성하는 것입니다.

        조건문: return mf == null ? null : makeRandomFileName(mf.getOriginalFilename());
        삼항 연산자를 사용하여 mf가 null인지 확인. 만약 mf가 null이라면, 메서드는 null을 반환합니다
        mf가 null이 아니라면, mf.getOriginalFilename()을 호출하여 파일의 원본 이름을 얻고
        이 이름을 사용하여 makeRandomFileName(String fileName) 메서드를 호출합니다. 그 결과, 새로운 랜덤 파일 이름이 생성되어 반환됩니다.

        makeRandomFileName(String fileName) 호출
        코드에서 makeRandomFileName(mf.getOriginalFilename())이 호출되는 부분은 String 타입의 파일 이름을
        매개변수로 받는 makeRandomFileName 메서드를 호출하라는 의미입니다.
        이 메서드는 전달받은 파일 이름을 기반으로 하여 랜덤한 파일 이름을 생성하고 반환하는 기능을 수행할 것입니다.

        원본 파일 이름을 가져와서 그 이름을 기반으로 한 랜덤 파일 이름을 생성해야 하는 상황에서 사용됩니다.
        예를 들어, 서버에 같은 이름의 파일이 여러 개 업로드되는 것을 방지하기 위해 사용될 수 있습니다.
         */
    }

    // 파일 저장 (target : 경로/파일명)
    public void transferTo(MultipartFile mf, String target) throws Exception {
        File savefile = new File(uploadPath, target); // 최종 경로
        // uploadPath = 파일이 저장되는 절대 경로

        mf.transferTo(savefile);

        /*
        이 코드는 MultipartFile 객체인 mf로 받은 파일을 서버의 지정된 경로(uploadPath)에 저장하는 기능을 수행
        public void transferTo(MultipartFile mf, String target) throws Exception
        transferTo 메서드는 MultipartFile 타입의 mf와 String 타입의 target을 매개변수로 받음
        이 메서드는 예외를 발생시킬 수 있으므로 호출하는 측에서 해당 예외를 처리해야 함을 나타내는 throws Exception을 포함 함

        파일 저장 위치 지정: File savefile = new File(uploadPath, target);
        File 객체 savefile을 생성하여 파일이 저장될 최종 경로를 지정합니다
        new File(uploadPath, target)는 uploadPath 디렉토리 안에 target 이름으로 파일을 생성하거나 찾는 데 사용됩니다.
        여기서 uploadPath는 파일이 저장되는 절대 경로를 나타내고, target은 저장될 파일의 이름(또는 상대 경로를 포함할 수도 있음)입니다.
        이 줄은 실제 파일이 저장될 위치와 이름을 설정하지만, 아직 파일의 내용을 이 위치에 쓰지는 않습니다.

        파일 저장 실행: mf.transferTo(savefile);
        MultipartFile 객체인 mf의 transferTo 메서드를 호출하여, 앞서 지정한 savefile 경로에 실제 파일을 저장합니다
        이 과정에서 파일의 모든 내용이 지정된 경로에 쓰여집니다.
        이 메서드는 파일을 저장하는 과정에서 다양한 예외를 발생시킬 수 있으므로, 호출하는 측에서 이러한 예외를 적절히 처리해야 합니다.

        요약
        new File(uploadPath, target); 부분은 서버에 파일을 저장할 위치와 이름을 지정합니다.
        이때 uploadPath는 파일이 저장될 기본 경로(절대 경로)이며, target은 해당 경로 내에서 파일을 저장할 때 사용할 이름입니다.
        mf.transferTo(savefile); 부분은 mf로 부터 받은 파일 데이터를 savefile로 지정된 경로와 이름으로 실제로 저장하는 과정을 수행합니다.
        파일이 실제로 디스크에 쓰여지는 순간입니다.
         */
    }
}
