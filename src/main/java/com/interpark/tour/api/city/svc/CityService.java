package com.interpark.tour.api.city.svc;

import com.interpark.tour.api.city.model.City;

import java.util.List;
import java.util.Map;

public interface CityService {

    List<City> cityAll();

    City cityById(Long cityId);

    City cityCreate(Map<String,String> nameMap);

    City cityUpdate(Long cityId, Map<String,String> nameMap);

    boolean cityDelete(Long cityId);

    List<City> cityList();

}
