package com.interpark.tour.api.city.ctr;

import com.interpark.tour.api.city.model.City;
import com.interpark.tour.api.city.svc.CityService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/cities")
@Slf4j
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    /**
     * City 전체 목록 조회
     * @return City 리스트
     */
    @GetMapping
    public ResponseEntity<List<City>> cityAll(){

        List<City> cities = cityService.cityAll();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cities);
    }

    /**
     * City 단일 조회
     * @param cityId 조회를 원하는 cityId
     * @return City
     */
    @GetMapping("/{cityId}")
    public ResponseEntity<City> cityById(@PathVariable Long cityId){

        City city = cityService.cityById(cityId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(city);
    }

    /**
     * City 등록
     * @param nameMap City 이름 ( key = "name" ) ex) {name=도쿄}
     * @return 생성된 City 정보
     */
    @PostMapping
    public ResponseEntity<City> cityCreate(@RequestBody Map<String,String> nameMap){



        City city = cityService.cityCreate(nameMap);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(city);
    }

    /**
     * City 수정
     * @param cityId 변경을 원하는 City 의 Id
     * @param name 변경될 city 이름
     * @return 변경된 City 정보
     */
    @PatchMapping("/{cityId}")
    public ResponseEntity<City> cityUpdate(@PathVariable Long cityId,@RequestBody String name){

        City city = cityService.cityUpdate(cityId,name);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(city);
    }

    /**
     * City 삭제
     * @param cityId 삭제를 원하는 cityId
     * @return 삭제된 City 정보
     */
    @DeleteMapping("/{cityId}")
    public ResponseEntity<Boolean> cityDelete(@PathVariable Long cityId){

        boolean res = cityService.cityDelete(cityId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(res);
    }

    /**
     * 특정 City 10개 조회
     * @param
     * @return 관심있을만한 City 10개 조회 (더 많을 수 있음)
     */
    @GetMapping("/list")
    public ResponseEntity<List<City>> cityList(){

        List<City> cities = cityService.cityList();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(cities);
    }

}
