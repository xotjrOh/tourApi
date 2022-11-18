package com.interpark.tour.api.city.service.impl;

import com.interpark.tour.api.city.model.City;
import com.interpark.tour.api.city.repository.CityRepository;
import com.interpark.tour.api.city.service.CityService;
import com.interpark.tour.api.lookup.service.LookupService;
import com.interpark.tour.api.tour.repository.TourRepository;
import com.interpark.tour.api.tour.service.TourService;
import com.interpark.tour.common.exception.CityException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class CityServiceImpl implements CityService {

    private final CityRepository cityRepository;
    private final TourRepository tourRepository;
    private final LookupService lookupService;
    private final TourService tourService;

    @Override
    @Transactional(readOnly = true)
    public List<City> cityAll() {

        return cityRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public City cityById(Long cityId) {

        City city = cityRepository.findById(cityId).orElseThrow(() -> new CityException(cityId + "에 해당하는 도시가 없습니다"));

        return city;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public City cityCreate(Map<String,String> nameMap) {

        // key값이 name인지 체크
        String name = nameCheck(nameMap);
        // value가 중복인지 체크
        List<String> cityNames = cityAll().stream()
                                        .map(City::getName)
                                        .collect(Collectors.toList());
        if (cityNames.contains(name)){
            throw new CityException("이미 존재하는 도시명입니다");
        }

        City city = cityRepository.save(new City(name));

        return city;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public City cityUpdate(Long cityId, Map<String,String> nameMap) {

        City city = cityRepository.findById(cityId).orElseThrow(() -> new CityException(cityId + "에 해당하는 도시가 없습니다"));
        String name = nameCheck(nameMap);
        city.setName(name);
        City updatedCity = cityRepository.save(city);

        return updatedCity;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean cityDelete(Long cityId) {

        City city = cityRepository.findById(cityId).orElseThrow(() -> new CityException(cityId + "에 해당하는 도시가 없습니다"));

        // 단, 해당 도시가 지정된 여행이 없을 경우만 삭제 가능
        if (!tourRepository.findAllByDepartures(city).isEmpty()) {
            throw new CityException("tour에 startCity가 매핑되어 있어 삭제가 불가능합니다");
        }
        if (!tourRepository.findAllByArrivals(city).isEmpty()) {
            throw new CityException("tour에 arriveCity가 매핑되어 있어 삭제가 불가능합니다");
        }

        cityRepository.delete(city);

        return true;
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> cityRegistered() {

        List<String> cityNames = cityRepository.findAllByRegDateAfterOrderByRegDateDesc();

        return cityNames;
    }

    @Override
    @Transactional(readOnly = true)
    public List<String> cityListImportant(Long userId) {

        List<String> exCities = tourService.getTravelingCities(userId);

        List<String> citiesImportant = tourService.getPlanCities(userId);
        if (citiesImportant.size()>9){
            exCities.addAll(citiesImportant.subList(0, 10));
            return exCities;
        }

        List<String> regCities = cityRegistered();
        citiesImportant.addAll(regCities);
        if (citiesImportant.size()>9){
            exCities.addAll(citiesImportant.subList(0, 10));
            return exCities;
        }

        List<String> viewedCities = lookupService.viewedCityInWeek(userId);
        citiesImportant.addAll(viewedCities);
        if (citiesImportant.size()>9){
            exCities.addAll(citiesImportant.subList(0, 10));
            return exCities;
        }

        List<String> allCities = cityAll().stream()
                                        .map(city -> city.getName())
                                        .collect(Collectors.toList());

        for (String cityName : citiesImportant) {
            allCities.remove(cityName);
        }

        Collections.shuffle(allCities);
        citiesImportant.addAll(allCities);
        exCities.addAll(citiesImportant.subList(0, 10));

        return exCities;
    }

    static String nameCheck(Map<String,String> nameMap){
        if (nameMap.get("name")==null) {
            throw new CityException("name이라는 키값을 사용하여 json 데이터를 넘겨주세요");
        }

        return nameMap.get("name");
    }
}
