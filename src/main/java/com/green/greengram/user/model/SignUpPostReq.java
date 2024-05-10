package com.green.greengram.user.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class SignUpPostReq {
    @Schema(example = "mic", description = "유저 아이디", requiredMode = Schema.RequiredMode.REQUIRED)
    private String uid;
    @Schema(example = "1212", description = "유저 비밀번호", requiredMode = Schema.RequiredMode.REQUIRED)
    private String upw;
    @Schema(example = "홍길동", description = "유저 이름", requiredMode = Schema.RequiredMode.REQUIRED)
    private String nm;

    @JsonIgnore private long userId;
    // @JsonIgnore --> 프론트에서 데이터를 보낼때 @JsonIgnore 붙은 값은 무시해서 백엔드로 보내주지 않음
    // xml 파일에서<insert id="postUser" keyProperty="userId" useGeneratedKeys="true"> 이렇게 처리 해줌으로써
    // pk값을 userId로 반환 해줌

    // 만약 Req 가 아니라 Res(Response)에서 @JsonIgnore 를 썼다면 그 값만 제외하고 프론트로 반환해주겠다는 의미.

    @JsonIgnore private String pic;
}
