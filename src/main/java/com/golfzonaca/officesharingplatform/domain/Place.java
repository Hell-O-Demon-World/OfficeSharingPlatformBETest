package com.golfzonaca.officesharingplatform.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Entity
@NoArgsConstructor
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "COMPANY_ID")
    private Company company;

    @OneToOne
    @JoinColumn(name = "RATEPOINT_ID")
    private RatePoint ratePoint;

    @OneToOne
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

    @Column(name = "PLACE_NAME", nullable = false, length = 30)
    private String placeName;

    @Column(name = "PLACE_DESCRIPTION", nullable = false, length = 50)
    private String description;

    @Column(name = "PLACE_OPENDAYS", nullable = false)
    private String openDays;

    @Column(name = "PLACE_START", nullable = false)
    private LocalTime placeStart;

    @Column(name = "PLACE_END", nullable = false)
    private LocalTime placeEnd;

    @Column(name = "PLACE_ADDINFO", nullable = false)
    private String placeAddInfo;

    @Version
    private Timestamp version;

    //양방향 매핑
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "place")
    private List<Room> rooms = new LinkedList<>();

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "place")
    private List<PlaceImage> placeImages = new LinkedList<>();

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "place")
    private List<RoomImage> roomImages = new LinkedList<>();

    @Builder
    public Place(Company company, RatePoint ratePoint, Address address, String placeName, String description, String openDays, LocalTime placeStart, LocalTime placeEnd, String placeAddInfo) {
        this.company = company;
        this.ratePoint = ratePoint;
        this.address = address;
        this.placeName = placeName;
        this.description = description;
        this.openDays = openDays;
        this.placeStart = placeStart;
        this.placeEnd = placeEnd;
        this.placeAddInfo = placeAddInfo;
    }

    public void updateAddress(Address address) {
        this.address = address;
    }

    public void updatePlaceName(String placeName) {
        this.placeName = placeName;
    }

    public void updateDescription(String description) {
        this.description = description;
    }

    public void updateOpenDays(String openDays) {
        this.openDays = openDays;
    }

    public void updatePlaceStart(LocalTime placeStart) {
        this.placeStart = placeStart;
    }

    public void updatePlaceEnd(LocalTime placeEnd) {
        this.placeEnd = placeEnd;
    }

    public void updatePlaceAddInfo(String placeAddInfo) {
        this.placeAddInfo = placeAddInfo;
    }
}
