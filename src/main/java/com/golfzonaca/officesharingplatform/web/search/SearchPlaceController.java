package com.golfzonaca.officesharingplatform.web.search;

import com.golfzonaca.officesharingplatform.domain.Place;
import com.golfzonaca.officesharingplatform.service.search.SearchService;
import com.golfzonaca.officesharingplatform.web.search.dto.RequestFilterData;
import com.golfzonaca.officesharingplatform.web.search.dto.RequestSearchData;
import com.golfzonaca.officesharingplatform.web.search.dto.ResponseData;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RestController
@RequiredArgsConstructor
public class SearchPlaceController {

    private final SearchService searchService;

    @PostMapping("/main/search")
    public List<ResponseData> searchPlaces(@RequestBody RequestSearchData requestSearchData) {
        List<Place> searchPlaces = searchService.findPlaces(requestSearchData);
        return response(searchPlaces);
    }

    @PostMapping("/main/filter")
    public List<ResponseData> filterPlaces(@RequestBody RequestFilterData requestFilterData) {
        List<Place> filterPlaces = searchService.filterPlaces(requestFilterData);
        return response(filterPlaces);
    }

    private List<ResponseData> response(List<Place> resultList) {
        List<ResponseData> places = new ArrayList<>();
        for (Place place : resultList) {
            places.add(new ResponseData(place.getId(), place.getPlaceName(), place.getAddress(), place.getPlaceAddInfo()));
        }
        return places;
    }
}
