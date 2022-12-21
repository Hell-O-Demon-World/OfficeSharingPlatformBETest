package com.golfzonaca.officesharingplatform.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.sql.Timestamp;

@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Entity
@Table(name = "PLACEIMAGE")
@NoArgsConstructor
public class PlaceImage {

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

    @Version
    private Timestamp version;

    public PlaceImage(String uploadFileName, String storeFileName, String savedPath, Place place) {
        this.uploadFileName = uploadFileName;
        this.storeFileName = storeFileName;
        this.savedPath = savedPath;
        this.place = place;
    }
}
