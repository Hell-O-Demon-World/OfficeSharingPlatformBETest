package com.golfzonaca.officesharingplatform.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Entity
@Table(name = "ROOMIMAGE")
@NoArgsConstructor
public class RoomImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "UPLOAD_FILENAME", nullable = false)
    private String uploadFileName;

    @Column(name = "STORE_FILENAME", nullable = false)
    private String storeFileName;

    @Column(name = "SAVED_PATH", nullable = false)
    private String savedPath;

    @ManyToOne
    @JoinColumn(name = "PLACE_ID")
    private Place place;

    @ManyToOne
    @JoinColumn(name = "ROOMKIND_ID")
    private RoomKind roomKind;

    @Version
    private Long version;

    public RoomImage(String uploadFileName, String storeFileName, String savedPath, Place place, RoomKind roomKind) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
        this.savedPath = savedPath;
        this.place = place;
        this.roomKind = roomKind;
    }
}
