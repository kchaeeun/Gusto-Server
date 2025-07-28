package com.umc.gusto.domain.store.service;

import com.umc.gusto.domain.store.model.response.*;
import com.umc.gusto.domain.user.entity.User;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;


public interface StoreService {

    List<GetStoreResponse> getStores(User user, List<Long> storeIds);
    GetStoreDetailResponse getStoreDetail(User user, Long storeId, LocalDate visitedAt, Long reviewId);
    List<GetStoresInMapResponse> getStoresInMap(User user, Double latitude, Double longitude, int radius, List<Long> myCategoryIds, Boolean visited);
    List<GetPinStoreResponse> getPinStoresByCategoryAndLocation(User user, Long myCategoryId, Double latitude, Double longitude, int radius);
    Map<String, Object> getVisitedPinStores(User user, Long myCategoryId, Double latitude, Double longitude, int radius, Long lastStoreId, int size);
    Map<String, Object> getUnvisitedPinStores(User user, Long myCategoryId, Double latitude, Double longitude, int radius, Long lastStoreId, int size);
    List<GetStoreInfoResponse> searchStore(String keyword);
}
