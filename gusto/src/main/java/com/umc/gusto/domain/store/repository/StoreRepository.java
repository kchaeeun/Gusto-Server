package com.umc.gusto.domain.store.repository;

import com.umc.gusto.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StoreRepository extends JpaRepository<Store, Long> {
    @Query("SELECT s FROM Store s WHERE s.town.townCode = :townCode AND s.storeId IN :storeIds")
    List<Store> findByTownCodeAndStoreIds(String townCode, List<Long> storeIds);
    // 네이티브 쿼리
    @Query(value = """
        SELECT * FROM sotre
        WHERE ST_Distance_Sphere(location, POINT(:longitude, :latitude)) <= :radius
    """, nativeQuery = true)
    List<Store> findStoresWithinRadius(Double latitude, Double longitude, int radius);

    List<Store> findTop5ByStoreNameContains(String keyword);
}
