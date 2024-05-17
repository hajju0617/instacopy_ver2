package com.green.greengram.user.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class User {
    private long userId;
    private String uid;
    private String upw;
    private String nm;
    private String pic;
    private String createdAt;
    private String updatedAt;

    // @Setter 가 있다면 xml 파일에 있는 db 변수랑 이름이 같아야 됨 (AS 필수)
    // @AllArgsConstructor 생성자로 값을 넣을때 mybatis 를 쓰면 xml 에 db 변수랑 이름이 같지 않아도 되지만 순서가 중요하다

//    public User(long userId, String uid, String upw, String nm, String pic, String createdAt, String updatedAt) {
//        this.userId = userId;
//        this.uid = uid;
//        this.upw = upw;
//        this.nm = nm;
//        this.pic = pic;
//        this.createdAt = createdAt;
//        this.updatedAt = updatedAt;
//    }             // @AllArgsConstructor 의 기능

//    public long getUserId() {
//        return userId;
//    }
//    public String getUid() {
//        return uid;
//    }
//    public String getUpw() {
//        return upw;
//    }
//    public String getNm() {
//        return nm;
//    }
//    public String getPic() {
//        return pic;
//    }
//    public String getCreatedAt() {
//        return createdAt;
//    }
//    public String getUpdatedAt() {
//        return updatedAt;
//    }             // @Getter 의 기능
}
