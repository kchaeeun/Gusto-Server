package com.umc.gusto.domain.store.entity;

import com.umc.gusto.global.common.BaseTime;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@DynamicInsert
@DynamicUpdate
public class Store extends BaseTime {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, updatable = false)
    private Long storeId;

    @Column(columnDefinition = "VARCHAR(30)")
    private String storeName;

    @Column(columnDefinition = "DOUBLE(17,14)")
    private Double longitude;

    @Column(columnDefinition = "DOUBLE(17,15)")
    private Double latitude;

    // 인덱싱 처리를 위한 point 타입 컬럼 추가
    @Column(columnDefinition = "POINT")
    private String location;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "categoryId")
    private Category category;

    @Column(columnDefinition = "VARCHAR(20)")
    private String categoryString;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "stateId", nullable = false)
    private State state;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "cityId", nullable = false)
    private City city;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "townId", nullable = false)
    private Town town;

    @Column(nullable = false, columnDefinition = "VARCHAR(60)")
    private String address;

    @Column(columnDefinition = "VARCHAR(60)")
    private String oldAddress;

    @Column(columnDefinition = "VARCHAR(20)")
    private String contact;

    @Builder.Default
    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 10)
    private StoreStatus storeStatus = StoreStatus.ACTIVE;

    public enum StoreStatus {
        ACTIVE, INACTIVE, CLOSED
    }

    // store 생성 시 location을 위도/경도 값으로 세팅
    @PrePersist
    @PreUpdate
    public void updateLocation() {
        if (longitude != null && latitude != null) {
            this.location = String.format("POINT(%f %f)", longitude, latitude);
        }
    }

}

