package com.green.greengram.user;

import com.green.greengram.common.model.ResultDto;
import com.green.greengram.user.model.PatchPasswordReq;
import com.green.greengram.user.model.SignInPostReq;
import com.green.greengram.user.model.SignInRes;
import com.green.greengram.user.model.SignUpPostReq;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import io.swagger.v3.oas.models.security.SecurityScheme;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/user")
@Tag(name = "유저 컨트롤러", description = "유저 CRUD sign-in, sign-out")
public class UserController {
    private final UserService service;

    @PostMapping("sign-up")         // /api/user/sign-up 붙여서 사용
    @Operation(summary = "회원가입", description = "프로필 사진은 필수가 아님")
    public ResultDto<Integer> postUser(@RequestPart(required = false) MultipartFile pic     // (required = false) : 필수값이 아니라는 뜻, 디폴트값은 true
            , @RequestPart SignUpPostReq p) {
        /*
        @RequestPart의 기능:
        1. 멀티파트 요청의 특정 부분을 컨트롤러의 메소드 매개변수로 바인딩 함
        2. 주로 파일 데이터(MultipartFile)를 처리하는 데 사용되지만, JSON이나 XML 같은 복잡한 데이터 타입을 매핑하는 데에도 사용될 수 있음
        3. required 속성을 통해 해당 파트가 필수인지 여부를 지정할 수 있음 required=false로 설정하면, 해당 파트가 없어도 요청 처리가 가능

        왜 @RequestPart SignUpPostReq p가 사용?
        위 코드에서 @RequestPart SignUpPostReq p는 멀티파트 요청의 일부로 전달된 사용자 정의 객체(SignUpPostReq)를 매핑하기 위해 사용되었음
        이 경우, 클라이언트는 회원가입을 위해 프로필 사진(MultipartFile pic)과 함께 SignUpPostReq 타입의 데이터(예: 사용자 이름, 비밀번호, 이메일 등)를
        JSON이나 다른 형식으로 전송할 수 있습니다.

        1. 멀티파트 데이터 처리: 회원가입 과정에서 사용자가 프로필 사진과 함께 다른 정보를 제공하는 경우, 이를 효과적으로 처리하기 위해 @RequestPart를 사용
        2. 유연성: @RequestPart는 필수가 아닌 파트(required = false로 설정된 MultipartFile pic)와 함께 사용자 정의 타입의 데이터를 처리할 수 있는 유연성을 제공함.
        3. 복잡한 데이터 타입 처리: SignUpPostReq와 같은 사용자 정의 타입을 직접 매핑하여, JSON 등의 복잡한 데이터 구조를 쉽게 서버에서 받아 처리할 수 있습니다.

        결론적으로, @RequestPart SignUpPostReq p는 사용자로부터 받은 멀티파트 요청 데이터 중
        프로필 사진 외에 추가적인 회원 정보를 포함하는 부분을 서버에서 처리하기 위해 사용되었음.
        이는 파일과 다른 데이터 타입을 함께 처리해야 하는 상황에서 매우 유용합니다.
        */
        log.info("pic : {}", pic);      // pic 문자열 변환 값이 {} 안에 삽입
        log.info("p: {}", p);
        int result = service.postSignUp(pic, p);

        return ResultDto.<Integer>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg("회원가입 성공")
                .resultData(result).build();
    }

    @PostMapping("sign-in")
    @Operation(summary = "인증처리", description = "")
    public ResultDto<SignInRes> postSignIn(@RequestBody SignInPostReq p) {
        SignInRes result = service.postSignIn(p);

        return ResultDto.<SignInRes>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg("인증 성공")
                .resultData(result)
                .build();
    }

    @PatchMapping("password-patch")
    public ResultDto<Integer> patchPassword(@RequestBody PatchPasswordReq p) {
        int result = service.patchPassword(p);

        return ResultDto.<Integer>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg("변경성공")
                .resultData(result)
                .build();
    }





}
