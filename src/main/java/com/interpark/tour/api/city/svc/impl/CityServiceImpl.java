package com.interpark.tour.api.city.svc.impl;

import com.interpark.tour.api.city.model.City;
import com.interpark.tour.api.city.repo.CityRepository;
import com.interpark.tour.api.city.svc.CityService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;

    @Override
    @Transactional(readOnly = true)
    public List<City> cityAll() {

        return cityRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public City cityById(Long cityId) {

        City city = cityRepository.findById(cityId).orElseThrow(() -> new NoSuchElementException(cityId + "에 해당하는 도시가 없습니다"));

        return city;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public City cityCreate(String name) {

        City city = cityRepository.save(new City(name));

        return city;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public City cityUpdate(Long cityId, String name) {

        City city = cityRepository.findById(cityId).orElseThrow(() -> new NoSuchElementException(cityId + "에 해당하는 도시가 없습니다"));
        city.setName(name);
        City updatedCity = cityRepository.save(city);

        return updatedCity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean cityDelete(Long cityId) {

        City city = cityRepository.findById(cityId).orElseThrow(() -> new NoSuchElementException(cityId + "에 해당하는 도시가 없습니다"));
        // 단, 해당 도시가 지정된 여행이 없을 경우만 삭제 가능

        cityRepository.delete(city);

        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public List<City> cityList() {

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

        return Collections.emptyList();
    }
}
