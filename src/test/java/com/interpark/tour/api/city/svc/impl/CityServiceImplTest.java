package com.interpark.tour.api.city.svc.impl;

import com.interpark.tour.api.city.model.City;
import com.interpark.tour.api.city.repo.CityRepository;
import com.interpark.tour.api.city.svc.CityService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("prod")
@SpringBootTest
class CityServiceImplTest {

    @Autowired
    CityService cityService;
    @Autowired
    CityRepository cityRepository;

    City cityIns;

    @BeforeEach
    void cleanup(){
        cityRepository.deleteAll();
        // given
        cityIns = cityRepository.save(new City("서울"));
        cityRepository.save(new City("도쿄"));
        cityRepository.save(new City("제주도"));
    }

    @Test
    void cityAll() {
        // when
        List<City> cities = cityService.cityAll();
        // then
        assertThat(cities.size()).isEqualTo(3);
    }

    @Test
    void cityById() {
        // when
        City city = cityService.cityById(cityIns.getId());
        // then
        assertThat(city.getName()).isEqualTo("서울");
    }

    @Test
    void cityCreate() {
        // when
        Map<String,String> nameMap = new HashMap<String, String>();
        nameMap.put("name","파리");
        City city = cityService.cityCreate(nameMap);
        // then
        assertThat(city.getName()).isEqualTo("파리");
    }

    @Test
    void cityUpdate() {
        // when
        Map<String,String> nameMap = new HashMap<String, String>();
        nameMap.put("name","파리");
        City city = cityService.cityUpdate(cityIns.getId(), nameMap);
        // then
        assertThat(city.getId()).isEqualTo(cityIns.getId());
        assertThat(city.getName()).isEqualTo("파리");
    }

    @Test
    void cityDelete() {
        // when
        boolean res = cityService.cityDelete(cityIns.getId());
        // then
        assertThat(res).isEqualTo(true);
    }

//    @Test
//    void cityList() {
//        // when
//        List<City> cities = cityService.cityAll();
//        // then
//        assertThat(cities.size()).isEqualTo(3);
//    }
}