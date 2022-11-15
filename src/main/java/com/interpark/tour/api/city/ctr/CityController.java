package com.interpark.tour.api.city.ctr;

//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;

import com.interpark.tour.api.city.model.City;
import com.interpark.tour.api.city.repo.CityRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@Slf4j
@RequestMapping("/api/v1/cities")
@RequiredArgsConstructor
public class CityController {

    private final CityRepository cityRepository;

    /**
     * City 전체 목록 조회
     * @return City 리스트
     */
    @GetMapping
    public ResponseEntity<List<City>> cityAll(){

        List<City> city = cityRepository.findAll();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(city);
    }

    /**
     * City 단일 조회
     * @param cityId 조회를 원하는 cityId
     * @return City
     */
    @GetMapping("/{cityId}")
    public ResponseEntity<City> cityById(@PathVariable Long cityId){

        City city = cityRepository.findById(cityId).orElseThrow(() -> new NoSuchElementException(cityId + "에 해당하는 도시가 없습니다"));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(city);
    }

    /**
     * City 등록
     * @param name City 이름
     * @return 생성된 City 정보
     */
    @PostMapping
    public ResponseEntity<City> cityCreate(@RequestBody String name){

        City city = cityRepository.save(new City(name));

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(city);
    }

    /**
     * City 수정
     * @param cityId 변경을 원하는 cityId
     * @param name 변경될 city 이름
     * @return 변경된 City 정보
     */
    @PatchMapping("/{cityId}")
    public ResponseEntity<City> cityUpdate(@PathVariable Long cityId, @RequestBody String name){

        City city = cityRepository.findById(cityId).orElseThrow(() -> new NoSuchElementException(cityId + "에 해당하는 도시가 없습니다"));
        city.setName(name);
        City updatedCity = cityRepository.save(city);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(updatedCity);
    }

    /**
     * City 삭제
     * @param cityId 삭제를 원하는 cityId
     * @return 삭제된 City 정보
     */
    @DeleteMapping("/{cityId}")
    public ResponseEntity<City> cityDelete(@PathVariable Long cityId){

        City city = cityRepository.findById(cityId).orElseThrow(() -> new NoSuchElementException(cityId + "에 해당하는 도시가 없습니다"));
        // 단, 해당 도시가 지정된 여행이 없을 경우만 삭제 가능

        cityRepository.delete(city);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(city);
    }

    @GetMapping
    public ResponseEntity<List<City>> cityInterest(){

        // 관련 로직
//        사용자별 도시 목록 조회 API
//        기본적으로 중복 없이 상위 10개 도시만 노출 (Pagination 없음)
//        단, 여행 중인 도시는 중복이 허용되며 노출 개수와 무관
//        도시 노출 순서 (위에서 아래 순서대로 노출)
//        여행 중인 도시 : 여행 시작일이 빠른 것부터
//        여행이 예정된 도시 : 여행 시작일이 가까운 것부터
//        하루 이내에 등록된 도시 : 가장 최근에 등록한 것부터
//        최근 일주일 이내에 한 번 이상 조회된 도시 : 가장 최근에 조회한 것부터
//        위의 조건에 해당하지 않는 모든 도시 : 무작위

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(Collections.emptyList());
    }

}
