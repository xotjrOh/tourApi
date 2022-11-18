package com.interpark.tour.api.city.service.impl;

import com.interpark.tour.api.city.model.City;
import com.interpark.tour.api.city.repository.CityRepository;
import com.interpark.tour.api.city.service.CityService;
import com.interpark.tour.api.tour.model.Tour;
import com.interpark.tour.api.tour.repository.TourRepository;
import com.interpark.tour.api.user.model.User;
import com.interpark.tour.api.user.repository.UserRepository;
import com.interpark.tour.common.exception.CityException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@ActiveProfiles("prod") // cityRegistered, cityListImportant 때문
@SpringBootTest
class CityServiceImplTest {

    @Autowired
    CityService cityService;
    @Autowired
    CityRepository cityRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    TourRepository tourRepository;

    City cityIns;


    @BeforeEach
    void given(){
        // cleanup
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
    void cityDeleteMappingError() throws Exception {
        // given
        userRepository.save(new User("오태석", "예삐공주"));
        tourRepository.save(new Tour(
                cityRepository.findByName("서울").get(),
                cityRepository.findByName("제주도").get(),
                LocalDateTime.now(),
                LocalDateTime.now(),
                userRepository.findByName("오태석").get()
        ));
        // then
        assertThatThrownBy(() -> cityService.cityDelete(cityIns.getId()))
                .isInstanceOf(CityException.class)
                .hasMessage("tour에 startCity가 매핑되어 있어 삭제가 불가능합니다");

        tourRepository.deleteAll();
        userRepository.deleteAll();
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
        // given, 연관관계 추가 필요
        userRepository.deleteAll();
        tourRepository.deleteAll();

        userRepository.save(new User("오태석", "예삐공주"));
        userRepository.save(new User("안민우", "생명은 소중한거야"));
        userRepository.save(new User("이승현","깜둥쟁이"));

        cityRepository.save(new City("도시1"));
        cityRepository.save(new City("도시2"));
        cityRepository.save(new City("도시3"));
        cityRepository.save(new City("도시4"));
        cityRepository.save(new City("도시5"));
        cityRepository.save(new City("도시6"));
        cityRepository.save(new City("도시7"));
        cityRepository.save(new City("도시8"));
        cityRepository.save(new City("도시9"));
        cityRepository.save(new City("도시10"));

        LocalDateTime localDateTime = LocalDateTime.now();
        tourRepository.save(new Tour(
                cityRepository.findByName("서울").get(),
                cityRepository.findByName("도쿄").get(),
                localDateTime.minusDays(2),
                localDateTime.plusDays(1),
                userRepository.findByName("안민우").get()
        ));
        tourRepository.save(new Tour(
                cityRepository.findByName("서울").get(),
                cityRepository.findByName("제주도").get(),
                localDateTime.minusDays(5),
                localDateTime.plusDays(2),
                userRepository.findByName("오태석").get()
        ));
        tourRepository.save(new Tour(
                cityRepository.findByName("서울").get(),
                cityRepository.findByName("도쿄").get(),
                localDateTime.plusDays(1),
                localDateTime.plusDays(4),
                userRepository.findByName("이승현").get()
        ));
        tourRepository.save(new Tour(
                cityRepository.findByName("도쿄").get(),
                cityRepository.findByName("서울").get(),
                localDateTime.plusDays(2),
                localDateTime.plusDays(5),
                userRepository.findByName("오태석").get()
        ));

        List<String> expectNames = Arrays.asList("제주도","서울","도시10","도시9","도시8","도시7","도시6","도시5","도시4","도시3","도시2");

        // when
        List<String> cityNames = cityService.cityListImportant(userRepository.findByName("오태석").get().getId());
        // then
        assertThat(cityNames).isEqualTo(expectNames);

        tourRepository.deleteAll();
        userRepository.deleteAll();
    }
}