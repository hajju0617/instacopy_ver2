package com.green.greengram.feed.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Data
public class FeedPostReq {
    @JsonIgnore private long feedId;

    private long userId;
    private String contents;
    private String location;

//    mybatis 에 보내주는 값 : userId, contests, location / 리턴값으로 pk값 받아옴
}
