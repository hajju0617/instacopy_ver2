package com.green.greengram.feedfavorite;

import com.green.greengram.feedfavorite.model.FeedFavoriteToggleReq;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class FeedFavoriteService {
    private final FeedFavoriteMapper mapper;

    public int toggleFavorite(FeedFavoriteToggleReq p) {
        /* 방법론 1.
        insert 를 해서 에러가 터지면 delete

        방법론 2.
        delete -> 1 -> return 0 / delete -> 0 -> insert return 1;
         */
        // result == 0 (좋아요 취소, 좋아요 -> no 좋아요 : "좋아요 취소")
        // result == 1 (좋아요, no 좋아요 -> 좋아요 : "좋아요 처리")
        int delAffectedRows = mapper.delFeedFavorite(p);
        if(delAffectedRows == 1) {
            return 0;
        }
        return mapper.insFeedFavorite(p);
    }
}
