package com.golfzonaca.officesharingplatform.web.mypage;

import com.golfzonaca.officesharingplatform.annotation.TokenUserId;
import com.golfzonaca.officesharingplatform.domain.MyPage;
import com.golfzonaca.officesharingplatform.service.mypage.MyPageService;
import com.golfzonaca.officesharingplatform.web.mypage.form.MyPageReservationForm;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("mypage")
public class MyPageController {
    private final MyPageService myPageService;

    @GetMapping
    public MyPage myPageForm(@TokenUserId Long userId) {
        return myPageService.createMyPageForm(userId);
    }

//    @GetMapping("/usage")
//    public MyPageReservationForm usageHistory(@TokenUserId Long userID) {
////                        .myPageReservationList(getMyPageReservationListByUserId(userId))
//
//    }

    @PostMapping("/cancel")
    public void cancelReservation(@TokenUserId Long userId, @RequestParam Integer order){
        myPageService.cancelByOrderAndUserId(order, userId);
    }
}
