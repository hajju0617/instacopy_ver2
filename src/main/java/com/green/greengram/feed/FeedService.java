package com.green.greengram.feed;

import com.green.greengram.common.CustomFileUtils;
import com.green.greengram.feed.model.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class FeedService {
    private final FeedMapper mapper;
    private final CustomFileUtils customFileUtils;

    @Transactional
    public FeedPostRes postFeed(List<MultipartFile> pics, FeedPostReq p) {
        int result = mapper.postFeed(p);



        FeedPicPostDto picDto = FeedPicPostDto.builder().feedId(p.getFeedId()).build();
        try {
            String path = String.format("feed/%d", p.getFeedId());
            customFileUtils.makeFolders(path);
        /*
        FeedPicPostDto 인스턴스를 생성하는 데 있어서 "빌더 패턴(Builder Pattern)"을 사용하고 있음
        빌더 패턴은 객체의 생성 과정과 표현 방법을 분리하여 동일한 생성 절차에서 서로 다른 표현 결과를 만들 수 있게 하는 디자인 패턴
        FeedPicPostDto.builder()는 FeedPicPostDto 의 빌더 객체를 생성
        이 빌더 객체는 FeedPicPostDto 객체를 단계적으로 구성할 수 있는 메서드(예: feedId(...))를 제공 함
        .builder(): FeedPicPostDto 객체를 생성하기 위한 빌더 객체를 초기화 함.
        .feedId(p.getFeedId()): 빌더에 feedId 값을 설정 함. 여기서 p.getFeedId()는 feedId의 값을 제공합니다.
        .build(): 앞서 설정된 값들을 바탕으로 최종적인 FeedPicPostDto 객체를 생성
        이러한 방식을 통해, 필요한 속성을 설정하고, 설정된 속성들을 가지고 객체를 생성하는 과정을 더 명확하게 표현
        빌더 패턴은 특히 생성자의 매개변수가 많거나 객체의 생성 과정이 복잡할 때 유용하게 사용
         */

            for(MultipartFile pic : pics) {     // 랜덤한 파일명.확장자 만들어주고 + 이동시킴
                String saveFileName = customFileUtils.makeRandomFileName(pic);
                picDto.getFileNames().add(saveFileName);
                String target = String.format("%s/%s", path, saveFileName);
                customFileUtils.transferTo(pic, target);
            }
            int affectedRowsPics = mapper.postFeedPics(picDto);

        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Feed 등록 오류");
        }
        return FeedPostRes.builder()
                .feedId(p.getFeedId())
                .pics(picDto.getFileNames())
                .build();
            // 피드를 올리자마자 삭제한다거나 댓글을 쓴다거나 할때 pk값이 필요하므로 pk값을 반환 (프론트에서 필요해서)
    }   //@Transactional 범위 끝

    /*
    N + 1에서, 1은 한 엔티티를 조회하기 위한 쿼리의 개수이며
    N은 조회된 엔티티의 개수만큼 연관된 데이터를 조회하기 위한 추가적인 쿼리의 개수를 의미
     */
    public List<FeedGetRes> getFeed(FeedGetReq p) {
        List<FeedGetRes> list = mapper.getFeed(p);
        log.info("list : {}", list);

        for(FeedGetRes res : list) {
            List<String> pics = mapper.getFeedPicsByFeedId(res.getFeedId());    // 이 부분이 n을 뜻함
            res.setPics(pics);
        }
        log.info("list : {}", list);
        return list;

    }
}

