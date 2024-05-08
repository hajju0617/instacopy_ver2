package com.green.greengram.user.model;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter                 // Setter 뺀 이유 : 빌더로 데이터를 넣으니깐
@Builder
@NoArgsConstructor  // 기본생성자를 자동으로 만들어주는 Lombok의 기능
@AllArgsConstructor
public class SignInRes {
    @Schema(description = "유저PK")
    private long userId;

    @Schema(description = "유저 이름")
    private String nm;

    @Schema(description = "유저 프로필 이미지")
    private String pic;
}
