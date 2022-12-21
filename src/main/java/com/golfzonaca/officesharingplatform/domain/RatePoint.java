package com.golfzonaca.officesharingplatform.domain;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Entity
@Table(name = "ratepoint")
@NoArgsConstructor
public class RatePoint {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "RATINGPOINT", nullable = false)
    private Float ratingPoint;

    @Version
    private Long version;

    @Builder
    public RatePoint(float ratingPoint) {
        this.ratingPoint = (float) (Math.round(ratingPoint * 10) / 10);
    }

    public void UpdateRatePoint(float ratingPoint) {
        this.ratingPoint = (float) (Math.round(ratingPoint * 10) / 10);
    }

}
