package com.golfzonaca.officesharingplatform.service.payment.requestform;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class RequestBodyCancelPaymentConverter {

    private String cid;
    private String tid;
    private Integer cancelAmount;
    private Integer cancelTaxFreeAmount;


    public RequestBodyCancelPaymentConverter toEntity(String cid, String tid, Integer cancelAmount, Integer cancelTaxFreeAmount) {
        return RequestBodyCancelPaymentConverter.builder()
                .cid(cid)
                .tid(tid)
                .cancelAmount(cancelAmount)
                .cancelTaxFreeAmount(cancelTaxFreeAmount)
                .build();
    }
}
