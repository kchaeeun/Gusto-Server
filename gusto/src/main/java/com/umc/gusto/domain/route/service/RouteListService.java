package com.umc.gusto.domain.route.service;

import com.umc.gusto.domain.route.entity.Route;
import com.umc.gusto.domain.route.model.request.RouteListRequest;
import com.umc.gusto.domain.route.model.response.RouteListResponse;

import java.util.List;


public interface RouteListService {

    // 루트 리스트 생성
    void createRouteList(Route route, List<RouteListRequest.createRouteListDto> request);

    // 루트 리스트 항목 삭제
    void deleteRouteList(Long routeListId);

    // 리스트 거리 조회
    List<RouteListResponse.RouteList> getRouteListDistance(Long routeId);

    // 루트 상세 조회
    RouteListResponse.RouteListResponseDto getRouteListDetail(Long routeId);
}
