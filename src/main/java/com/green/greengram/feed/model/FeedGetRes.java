package com.green.greengram.feed.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@ToString
public class FeedGetRes {       // Res(응답) 는 JSON
    private long feedId;
    private long writerId;
    private String contents;
    private String location;
    private String createdAt;
    private String writerNm;
    private String writerPic;


    private List<String> pics;
}
