package com.green.greengram.feed.model;

import lombok.Builder;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Builder()
public class FeedPicPostDto {
    private long feedId;

    @Builder.Default
    private List<String> fileNames = new ArrayList<>();
    // "a.jpg", "b.jpg", "c.jpg"....
}

/*
INSERT INTO feed_pics
        ( feed_id , pic)        <!-- 여러개를 넣어야 해서 SET 안 씀 -->
        VALUES
        ( 10, 'a.jpg' )
        , ( 10, 'b.jpg')
        , ( 10, 'c.jpg')
        다음에 올 값이 있다면 , 를 찍음
 */