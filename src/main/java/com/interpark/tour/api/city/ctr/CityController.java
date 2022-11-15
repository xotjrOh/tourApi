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

import java.util.Optional;

@RestController
@Slf4j
@RequestMapping("/api/v1/cities")
@RequiredArgsConstructor
public class CityController {

    private final CityRepository cityRepository;

    @GetMapping("/{cityId}")
    public ResponseEntity<String> cityById(@PathVariable Long cityId){

//        cityRepository.findById(cityId).orElseThrow();

        Optional<City> city = cityRepository.findById(cityId);

        City cityIns = city.get();
        log.info("city = {}",cityIns);

//        if (cityIns.){
//            return ResponseEntity
//                    .status(HttpStatus.OK)
//                    .body(cityIns.toString());
//        } else {
//            String message = String.format("cityId %d에 해당하는 데이터가 없습니다", cityId);
//            return ResponseEntity
//                    .status(HttpStatus.BAD_REQUEST)
//                    .body(message);
//        }

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cityIns.toString());


    }

}
