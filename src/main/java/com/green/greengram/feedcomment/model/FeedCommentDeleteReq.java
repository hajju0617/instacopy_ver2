package com.green.greengram.feedcomment.model;

import lombok.Data;
import lombok.Getter;
import org.springframework.web.bind.annotation.BindParam;

import java.beans.ConstructorProperties;

@Getter
public class FeedCommentDeleteReq {
    private long feedCommentId;
    private long signedUserId;

    @ConstructorProperties({"feed_comment_id", "signed_user_id"})
    // feed_comment_id, signed_user_id : 프론트에서 보내주는 변수값? 을 long feedCommentId,long signedUserId 로 보내줌
    public FeedCommentDeleteReq(long feedCommentId,long signedUserId) {
        this.feedCommentId = feedCommentId;
        this.signedUserId = signedUserId;
    }
}
