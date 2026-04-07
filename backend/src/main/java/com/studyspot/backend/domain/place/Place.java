package com.studyspot.backend.domain.place;

import com.studyspot.backend.global.BaseEntity;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "places")
public class Place extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //카카오 장소 ID 저장용 필드
    @Column(unique = true)
    private String externalId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 255)
    private String address;

    private String category;

    private Double latitude;
    private Double longitude;

    @Column(name = "average_rating")
    private Double averageRating = 0.0;

    // 실시간 혼잡도 상태 (0: 여유, 1: 보통, 2: 혼잡)
    @Column(nullable = false)
    private Integer currentCongestion = 0;

    // 장소 태그
    @ElementCollection
    @CollectionTable(name = "place_tags", joinColumns = @JoinColumn(name = "place_id"))
    @Column(name = "tag_name")
    private List<String> tags = new ArrayList<>();

    @Builder
    public Place(String externalId, String name, String address, String category, Double latitude, Double longitude, List<String> tags) {
        Assert.hasText(name, "장소명은 필수입니다.");
        Assert.hasText(address, "주소는 필수입니다.");

        this.externalId = externalId; // 추가
        this.name = name;
        this.address = address;
        this.category = category;
        this.latitude = latitude;
        this.longitude = longitude;
        if (tags != null) this.tags = tags;
    }

    // 비즈니스 로직: 혼잡도 및 별점 업데이트
    public void updateCongestion(Integer congestion) {
        this.currentCongestion = congestion;
    }

    public void updateAverageRating(Double newAverage) {
        this.averageRating = newAverage;
    }
}