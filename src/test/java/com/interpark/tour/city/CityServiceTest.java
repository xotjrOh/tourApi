package com.interpark.tour.city;

import com.interpark.tour.api.city.model.City;
import com.interpark.tour.api.city.svc.CityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("prod")
@SpringBootTest
class CityServiceTest {

    @Autowired
    CityService cityService;

    City cityIns;

//    @BeforeEach
//    void cleanup(){
//        cityService.deleteAll();
//        // given
//        cityIns = cityService.save(new City("서울"));
//        cityService.save(new City("도쿄"));
//    }
//
//    @Test
//    @DisplayName("findByName")
//    void findByName(){
//        // when
//        String name = "서울";
//        Optional<City> cityOpt = cityService.findByName(name);
//        // then
//        City city = cityOpt.orElseThrow(() -> new NoSuchElementException(name + " (이)라는 도시가 없습니다"));
//        assertThat(city.getId()).isEqualTo(cityIns.getId());
//    }
//
//    @Test
//    @DisplayName("findAll")
//    void findAll(){
//        // when
//        List<City> cities = cityService.findAll();
//
//        // then
//        assertThat(cities.size()).isEqualTo(2);
//    }
//
//
//    @Test
//    @DisplayName("findAllByRegDateIsAfter")
//    void findAllByRegDateIsAfter(){
//        List<City> cities = cityService.findAllByRegDateAfterOrderByRegDateDesc();
//        //then
//        assertThat(cities.size()).isEqualTo(2);
//    }
}
