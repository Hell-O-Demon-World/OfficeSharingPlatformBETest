package com.golfzonaca.officesharingplatform.domain;

import com.golfzonaca.officesharingplatform.domain.type.MileagePaymentReason;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.sql.Timestamp;

@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Entity
@Table(name = "mileage_payment_update")
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MileagePaymentUpdate {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "UPDATE_POINT")
    private Long updatePoint;
    @Column(name = "UPDATE_REASON")
    @Enumerated(EnumType.STRING)
    private MileagePaymentReason paymentReason;
    @ManyToOne
    @JoinColumn(name = "MILEAGE_UPDATE_ID")
    private MileageUpdate mileageUpdate;
    @OneToOne
    @JoinColumn(name = "PAYMENT_ID")
    private Payment payment;

    @Version
    private Timestamp version;

    @Builder
    public MileagePaymentUpdate(Long updatePoint, MileagePaymentReason paymentReason, MileageUpdate mileageUpdate, Payment payment) {
        this.updatePoint = updatePoint;
        this.paymentReason = paymentReason;
        this.mileageUpdate = mileageUpdate;
        this.payment = payment;
    }
}
