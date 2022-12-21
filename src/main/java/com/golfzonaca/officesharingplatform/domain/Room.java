package com.golfzonaca.officesharingplatform.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "ROOM_KIND_ID")
    private RoomKind roomKind;

    @ManyToOne
    @JoinColumn(name = "PLACE_ID")
    private Place place;

    @Version
    private Timestamp version;

    //양방향 매핑
    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToMany(mappedBy = "room")
    private List<Reservation> reservationList = new LinkedList<>();

    @org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
    @OneToOne(mappedBy = "room")
    private RoomStatus roomStatus;

    @Builder
    public Room(RoomKind roomKind, Place place) {
        this.roomKind = roomKind;
        this.place = place;
    }
}
