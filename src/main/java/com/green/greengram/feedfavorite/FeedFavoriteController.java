package com.green.greengram.feedfavorite;

import com.green.greengram.common.model.ResultDto;
import com.green.greengram.feed.model.FeedPostRes;
import com.green.greengram.feedfavorite.model.FeedFavoriteToggleReq;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.boot.actuate.autoconfigure.observation.ObservationProperties;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("api/feed/favorite")
public class FeedFavoriteController {
    private final FeedFavoriteService service;

    @GetMapping
    @Operation(summary = "좋아요", description = "Toggle 처리")
    public ResultDto<Integer> toggleFavorite(@ParameterObject @ModelAttribute FeedFavoriteToggleReq p) {
        int result = service.toggleFavorite(p);     // result == 0 (좋아요 취소, 좋아요 -> 좋아요 no좋아요 : 좋아요 취소)
                                                    // result == 1 (좋아요, no좋아요 -> 좋아요 : 좋아요 처리)

        return ResultDto.<Integer>builder()
                .statusCode(HttpStatus.OK)
                .resultMsg(result == 1 ? "좋아요 처리" : "좋아요 취소")
                .resultData(result)
                .build();
    }
}

