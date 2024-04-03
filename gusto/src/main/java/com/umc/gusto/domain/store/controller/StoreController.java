package com.umc.gusto.domain.store.controller;


import com.umc.gusto.domain.store.model.response.GetStoreDetailResponse;
import com.umc.gusto.domain.store.model.response.GetStoreResponse;
import com.umc.gusto.domain.store.service.StoreService;
import com.umc.gusto.domain.user.entity.User;
import com.umc.gusto.global.auth.model.AuthUser;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreController {
    private final StoreService storeService;

    /**
     * 가게 1건 조회
     * [GET] /stores/{storeId}
     */
    @GetMapping("/{storeId}")
    public ResponseEntity<GetStoreResponse> getStore(
            @AuthenticationPrincipal AuthUser authUser,
            @PathVariable Long storeId) {
        User user = authUser.getUser();
        GetStoreResponse getStore = storeService.getStore(user, storeId);
        return ResponseEntity.status(HttpStatus.OK).body(getStore);
    }

    /**
     * 가게 1건 상세 조회
     * [GET] /stores/{storeId}/detail?reviewId={reviewId}
     */
    @GetMapping("/{storeId}/detail")
    public ResponseEntity<GetStoreDetailResponse> getStoreDetail(
            @AuthenticationPrincipal AuthUser authUser,
            @PathVariable Long storeId,
            @RequestParam(name = "reviewId", required = false) Long reviewId){
        User user = authUser.getUser();
        Pageable pageable = PageRequest.of(0, 3);

        // 상점 세부 정보 가져오기
        GetStoreDetailResponse getStoreDetail = storeService.getStoreDetail(user, storeId, reviewId, pageable);
        return  ResponseEntity.status(HttpStatus.OK).body(getStoreDetail);
    }

    /**
     * 리스트로 받은 가게 id 상세 조회
     * [GET] /stores
     */
    @GetMapping
    public ResponseEntity<GetStoreResponse> getStores(
            @AuthenticationPrincipal AuthUser authUser) {
        User user = authUser.getUser();
        GetStoreResponse getStore = storeService.getStore(user);
        return ResponseEntity.status(HttpStatus.OK).body(getStore);
    }

}
