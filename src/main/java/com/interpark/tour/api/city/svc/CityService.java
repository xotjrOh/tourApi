package com.interpark.tour.api.city.svc;

import com.interpark.tour.api.city.model.City;

import java.util.List;

public interface CityService {

    List<City> cityAll();

    City cityById(Long cityId);

    City cityCreate(String name);

    City cityUpdate(Long cityId, String name);

    boolean cityDelete(Long cityId);

    List<City> cityList();

}
