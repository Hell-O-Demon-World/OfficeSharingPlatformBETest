package com.golfzonaca.officesharingplatform.web.search.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalTime;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class SearchRequestData {
    private String searchWord;
    private String day;
    private LocalTime startTime;
    private LocalTime endTime;
    private String roomType;
}