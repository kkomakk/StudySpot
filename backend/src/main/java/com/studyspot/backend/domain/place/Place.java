package com.studyspot.backend.domain.place;

import com.studyspot.backend.global.BaseEntity;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "places")
public class Place extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String externalId;

    @Column(nullable = false, length = 100)
    private String name;

    @Column(nullable = false, length = 255)
    private String address;

    private String roadAddress;
    private String phone;
    private String placeUrl;
    private String category;

    @Column(name = "image_url")
    private String imageUrl;

    private Double latitude;
    private Double longitude;

    @Column(name = "average_rating")
    @Builder.Default
    private Double averageRating = 0.0;

    @Column(name = "review_count")
    @Builder.Default
    private Integer reviewCount = 0;

    @Column(nullable = false)
    @Builder.Default
    private Integer currentCongestion = 0;

    @ElementCollection
    @CollectionTable(name = "place_tags", joinColumns = @JoinColumn(name = "place_id"))
    @Column(name = "tag_name")
    @Builder.Default
    private List<String> tags = new ArrayList<>();

    @Builder
    public Place(String externalId, String name, String address, String roadAddress, String phone, String placeUrl, String category, Double latitude, Double longitude, List<String> tags, Integer reviewCount) {
        Assert.hasText(name, "장소명은 필수입니다.");
        Assert.hasText(address, "주소는 필수입니다.");

        this.externalId = externalId;
        this.name = name;
        this.address = address;
        this.roadAddress = roadAddress;
        this.phone = phone;
        this.placeUrl = placeUrl;
        this.category = category;
        this.latitude = latitude;
        this.longitude = longitude;
        this.reviewCount = (reviewCount != null) ? reviewCount : 0;
        if (tags != null) this.tags = tags;
    }

    public void updateStatistics(Integer newRating) {
        double totalRating = (this.averageRating * this.reviewCount) + newRating;

        this.reviewCount++;

        this.averageRating = Math.round((totalRating / this.reviewCount) * 10.0) / 10.0;
    }

    public void updateCongestion(Integer congestion) {
        this.currentCongestion = congestion;
    }
}