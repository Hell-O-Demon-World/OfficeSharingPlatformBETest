package com.golfzonaca.officesharingplatform.service.place;

import com.golfzonaca.officesharingplatform.domain.Place;
import com.golfzonaca.officesharingplatform.service.place.dto.PlaceDetailsInfo;
import com.golfzonaca.officesharingplatform.service.place.dto.PlaceListDto;
import com.google.gson.JsonObject;

import java.util.List;
import java.util.Map;

public interface PlaceService {
    List<Place> findAllPlaces();

    Place findById(long placeId);

    boolean isOpenDay(Long id, String day);

    boolean isOpenToday(Long id, String startTime);

    boolean selectedDateValidation(String startDate, String startDate1);

    Map<Integer, PlaceListDto> processingMainPlaceData(List<Place> places);

    PlaceDetailsInfo getPlaceDetailsInfo(long placeId);

    Map<String, JsonObject> getReviewData(Long placeId, long page);

    Map<String, JsonObject> getCommentData(Long reviewId, long page);
}
