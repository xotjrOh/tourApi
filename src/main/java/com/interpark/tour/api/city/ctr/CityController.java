package com.interpark.tour.api.city.ctr;

//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;

import com.interpark.tour.api.city.model.City;
import com.interpark.tour.api.city.repo.CityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.NoSuchElementException;

@RestController
@Slf4j
@RequestMapping("/api/v1/cities")
@RequiredArgsConstructor
public class CityController {

    private final CityRepository cityRepository;

    @GetMapping("/{cityId}")
    public ResponseEntity<City> cityById(@PathVariable Long cityId){

        City city = cityRepository.findById(cityId).orElseThrow(() -> new NoSuchElementException(cityId + "에 해당하는 도시가 없습니다"));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(city);
    }

}
