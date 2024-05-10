package com.green.greengram.feedcomment;

import com.green.greengram.common.model.ResultDto;
import com.green.greengram.feedcomment.model.FeedCommentDeleteReq;
import com.green.greengram.feedcomment.model.FeedCommentGetRes;
import com.green.greengram.feedcomment.model.FeedCommentPostReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("api/feed/comment")
public class FeedCommentController {
    private final FeedCommentService service;

    @PostMapping
    public ResultDto<Long> postFeedComment(@RequestBody FeedCommentPostReq p) {
        // get, delete(많은 데이터를 보내지 않음) 대부분 url 로 처리
        // url : pathVariable , QS(param,parameter)

        // post, put : 많은 데이터를 보내거나 정보를 표시하고 싶지 않을때(정보를 숨기고 싶을 때) 대부분 body
        // body : json, file

        /*
        RESTful API
        /api/board

        Created -> post
        Read -> get
        Read -> get --> /api/board/값 ==> pathVariable 방식 {중복을 피함} (일반적인 get 이 아닌 디테일하게 (게시글 하나) 조회하고 싶을때)
        Update -> put (많은 데이터를 수정할때)
        Update -> patch (보통 한개 정도 간단하게 수정할때) /api/board/값 ==> pathVariable 방식
        Delete -> delete
        ===> http 메소드로만 처리
         */
        long result = service.postFeedComment(p);   // result = feedCommentId

        return ResultDto.<Long>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg(HttpStatus.OK.toString())
                .resultData(result)
                .build();
    }

    @GetMapping
    public ResultDto<List<FeedCommentGetRes>> getFeedComment(@RequestParam(name="feed_id") long feedId) {
        List<FeedCommentGetRes> list = service.getFeedComment(feedId); //4~N

        return ResultDto.<List<FeedCommentGetRes>>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg(String.format("rows: %,d", list.size()))
                .resultData(list)
                .build();
    }

    @DeleteMapping
    public ResultDto<Integer> deleteFeedComment(@ParameterObject @ModelAttribute FeedCommentDeleteReq p) {
        System.out.println(p);
        int result = service.deleteFeedComment(p);

        return ResultDto.<Integer>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg(result == 0 ? "댓글 삭제를 할 수 없습니다." : "댓글을 삭제하였습니다.")
                .resultData(result)
                .build();
    }
}
