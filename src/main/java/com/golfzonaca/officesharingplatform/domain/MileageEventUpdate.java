package com.golfzonaca.officesharingplatform.domain;

import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "MILEAGE_EVENT_UPDATE")
public class MileageEventUpdate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "MILEAGE_UPDATE_ID")
    private MileageUpdate mileageUpdate;

    @Column(name = "UPDATE_POINT")
    private Long increasePoint;
    
    @Column(name = "INCREASE_REASON")
    private String increaseReason;

    @Version
    private Long version;
}
