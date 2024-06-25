package com.umc.gusto.domain.review.repository;

import com.umc.gusto.domain.review.entity.ReviewImages;
import com.umc.gusto.domain.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewImagesRepository extends JpaRepository<ReviewImages, Long> {
    ReviewImages findByStore(Store store);
}
