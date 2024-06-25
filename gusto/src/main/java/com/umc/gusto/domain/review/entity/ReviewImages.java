package com.umc.gusto.domain.review.entity;

import com.umc.gusto.domain.store.entity.Store;
import com.umc.gusto.global.common.BaseTime;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
public class ReviewImages extends BaseTime {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "reviewImagesId")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "storeId")
    private Store store;

    @Builder.Default
    @ElementCollection(fetch = FetchType.LAZY)
    @JoinTable(name = "reviewImgList", joinColumns = @JoinColumn(name = "reviewImgListId"))
    private List<String> reviewImgList = new ArrayList<>();

    public void updateReviewImgList(List<String> reviewImgList) {
        this.reviewImgList = reviewImgList;
    }

}
