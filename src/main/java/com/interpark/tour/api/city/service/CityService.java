package com.interpark.tour.api.city.service;

import com.interpark.tour.api.city.model.City;

import java.util.List;
import java.util.Map;

public interface CityService {

    List<City> cityAll();

    City cityById(Long cityId);

    City cityCreate(Map<String,String> nameMap);

    City cityUpdate(Long cityId, Map<String,String> nameMap);

    boolean cityDelete(Long cityId);

    List<String> cityRegistered();

    List<String> cityListImportant(Long userId);

}
