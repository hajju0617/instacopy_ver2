package com.green.greengram.feed.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.green.greengram.common.GlobalConst;
import lombok.Data;

@Data
public class FeedGetReq {
    private int page;
    private long loginedUserId;

    @JsonIgnore
    private int startIdx;
    @JsonIgnore
    private int len;

    public void setPage(int page) {
        this.page = page;
        this.len = GlobalConst.FEED_PAGE_ITEM_LEN;
        this.startIdx = (this.page - 1) * this.len;
    }
}
