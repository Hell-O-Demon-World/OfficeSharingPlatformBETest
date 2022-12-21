package com.golfzonaca.officesharingplatform.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.time.LocalDateTime;

@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Getter
@Entity
@NoArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "RATING_ID")
    private Rating rating;

    @Column(name = "COMMENT_TEXT", nullable = false, length = 40)
    private String text;

    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private User writer;

    @Column(name = "COMMENT_DATETIME", nullable = false)
    private LocalDateTime dateTime;

    @Version
    private Long version;

    @Builder
    public Comment(Rating rating, String text, User writer, LocalDateTime dateTime) {
        this.rating = rating;
        this.text = text;
        this.writer = writer;
        this.dateTime = dateTime;
    }

    public void updateComment(String text) {
        this.text = text;
    }
}
