package com.interpark.tour.api.city.svc.impl;

import com.interpark.tour.api.city.model.City;
import com.interpark.tour.api.city.repo.CityRepository;
import com.interpark.tour.api.city.svc.CityService;
import com.interpark.tour.api.user.repo.UserRepository;
import com.interpark.tour.cmm.exception.CityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ActiveProfiles("prod")
@SpringBootTest
class CityServiceImplTest {

    @Autowired
    CityService cityService;
    @Autowired
    CityRepository cityRepository;
    @Autowired
    UserRepository userRepository;

    City cityIns;

    // 연관 관계가 생긴 이후에는 초기화에서 오류가 발생한다
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
    @DisplayName("존재하지않는 id로 조회할 경우")
    void cityByWrongId() throws Exception {
        // when
        Long wrongId = cityIns.getId()+3l;
        // then
        assertThatThrownBy(() -> cityService.cityById(wrongId))
                .isInstanceOf(CityException.class)
                .hasMessage(wrongId+"에 해당하는 도시가 없습니다");
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
    @DisplayName("중복된 이름의 도시를 생성할 경우")
    void cityCreateDuplicateName() throws Exception {
        // when
        Map<String,String> nameMap = new HashMap<String, String>();
        nameMap.put("name","서울");
        // then
        assertThatThrownBy(() -> cityService.cityCreate(nameMap))
                .isInstanceOf(CityException.class)
                .hasMessage("이미 존재하는 도시명입니다");
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
    @DisplayName("update시 key값이 틀릴경우")
    void cityUpdateKeyError() throws Exception {
        // when
        Map<String,String> nameMap = new HashMap<String, String>();
        nameMap.put("Name","파리");
        // then
        assertThatThrownBy(() -> cityService.cityUpdate(cityIns.getId(), nameMap))
                .isInstanceOf(CityException.class)
                .hasMessage("name이라는 키값을 사용하여 json 데이터를 넘겨주세요");
    }

    @Test
    void cityDelete() {
        // when
        boolean res = cityService.cityDelete(cityIns.getId());
        // then
        assertThat(res).isEqualTo(true);
    }

    @Test
    void cityRegistered() {
        // when
        List<String> cityNames = cityService.cityRegistered();
        List<String> expectNames = Arrays.asList("제주도", "도쿄", "서울");
        // then
        assertThat(cityNames).isEqualTo(expectNames);
    }

    @Test
    void cityListImportant() {
        // when
        List<String> cityNames = cityService.cityListImportant(userRepository.findByName("오태석").get().getId());
        // then
        assertThat(cityNames.size()).isGreaterThan(9);
    }
}