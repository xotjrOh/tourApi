package com.interpark.tour.api.tour.service;

import com.interpark.tour.api.tour.model.Tour;
import com.interpark.tour.api.tour.model.TourDTO;

import java.util.List;

public interface TourService {

    List<Tour> tourAll();

    Tour tourById(Long tourId);

    Tour tourCreate(TourDTO tourDTO);

    Tour tourUpdate(Long tourId, TourDTO tourDTO);

    boolean tourDelete(Long tourId);

    List<String> getTravelingCities(Long userId);

    List<String> getPlanCities(Long userId);
}
