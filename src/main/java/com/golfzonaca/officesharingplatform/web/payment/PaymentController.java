package com.golfzonaca.officesharingplatform.web.payment;

import com.golfzonaca.officesharingplatform.domain.payment.KakaoPayApprovalResponse;
import com.golfzonaca.officesharingplatform.domain.payment.KakaoPayCancelResponse;
import com.golfzonaca.officesharingplatform.service.payment.PaymentService;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.CardInfo;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Setter
    private PaymentService paymentService;


    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/kakaoPay")
    public void kakaoPayGet() {
    }

    @PostMapping("/kakaoPay")
    public String kakaoPayReady(@RequestBody Map<String, String> reservationInfo) {
        Long reservationId = Long.valueOf(reservationInfo.get("reservationId"));
        String payWay = reservationInfo.get("payWay");
        String payType = reservationInfo.get("payType");
        return "redirect:" + paymentService.kakaoPayReadyRequest(reservationId, payWay, payType);
    }

    @GetMapping("/{reservationId}/kakaoPayApprove")
    public KakaoPayApprovalResponse kakaoPayApprove(@PathVariable long reservationId, @RequestParam("pg_token") String pg_token, RedirectAttributes redirectAttributes) {
        redirectAttributes.addAttribute("pg_token", pg_token);
        redirectAttributes.addAttribute("reservationId", reservationId);
        return paymentService.kakaoPayApprovalRequest(reservationId, pg_token);
    }

    @PostMapping("/kakaoPayCancel")
    public KakaoPayCancelResponse kakaoPayCancel(@RequestBody Map<String, Long> reservationInfo) {
        Long reservationId = reservationInfo.get("reservationId");
        return paymentService.kakaoPayCancelRequest(reservationId);
    }

    @PostMapping("/nicepay")
    public IamportResponse<Payment> nicePay(@RequestBody Map<String, String> nicePayInfo) throws IamportResponseException, IOException {
        String cardNumber = nicePayInfo.get("card_number");
        String expiry = nicePayInfo.get("expiry");
        String birth = nicePayInfo.get("birth");
        String pwd2digit = nicePayInfo.get("pwd_2digit");

        CardInfo cardInfo = new CardInfo(cardNumber, expiry, birth, pwd2digit);
        return paymentService.nicePay(cardInfo);
    }

    @PostMapping("/nicePaycancel")
    public IamportResponse<Payment> nicePayCancel(@RequestBody Map<String, String> nicePayInfo) throws IamportResponseException, IOException {
        return paymentService.nicePayCancel();
    }

}
