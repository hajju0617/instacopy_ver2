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
