package com.umc.gusto.domain.review.service;

import com.umc.gusto.domain.review.entity.Review;
import com.umc.gusto.domain.review.entity.ReviewImages;
import com.umc.gusto.domain.review.repository.ReviewImagesRepository;
import com.umc.gusto.domain.review.repository.ReviewRepository;
import com.umc.gusto.domain.store.entity.Store;
import com.umc.gusto.domain.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReviewImageServiceImpl {
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final ReviewImagesRepository reviewImagesRepository;

    @Transactional
    @Scheduled(cron = "0 0 0 * * MON")
    public void updateStoreReviewImages() {
        List<Store> stores = storeRepository.findAll();

        for (Store store : stores) {
            List<Review> top4Reviews = reviewRepository.findFirst4ByStoreOrderByLikedDesc(store);
            List<String> reviewImages = top4Reviews.stream()
                    .map(Review::getImg1)
                    .collect(Collectors.toList());

            ReviewImages storeReviewImages = reviewImagesRepository.findByStore(store);

            if (storeReviewImages == null) {
                storeReviewImages = ReviewImages.builder()
                        .store(store)
                        .reviewImgList(reviewImages)
                        .build();
            } else {
                storeReviewImages.updateReviewImgList(reviewImages);
            }
            reviewImagesRepository.save(storeReviewImages);
        }
    }
}
