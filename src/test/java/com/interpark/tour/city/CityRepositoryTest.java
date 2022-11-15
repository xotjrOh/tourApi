package com.interpark.tour.city;

import com.interpark.tour.api.city.model.City;
import com.interpark.tour.api.city.repo.CityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@ActiveProfiles("prod")
@SpringBootTest
class CityRepositoryTest {

    @Autowired
    CityRepository cityRepository;

    City cityIns;

    @BeforeEach
    void cleanup(){
        cityRepository.deleteAll();
        // given
        cityIns = cityRepository.save(new City("서울"));
        cityRepository.save(new City("도쿄"));
    }

    @Test
    @DisplayName("findByName")
    void findByName(){
        // when
        String name = "서울";
        Optional<City> cityOpt = cityRepository.findByName(name);
        // then
        City city = cityOpt.orElseThrow(() -> new NoSuchElementException(name + " (이)라는 도시가 없습니다"));
        assertThat(city.getId()).isEqualTo(cityIns.getId());
    }

    @Test
    @DisplayName("findAll")
    void findAll(){
        // when
        List<City> cities = cityRepository.findAll();

        // then
        assertThat(cities.size()).isEqualTo(2);
    }


    @Test
    @DisplayName("findAllByRegDateIsAfter")
    void findAllByRegDateIsAfter(){
        List<City> cities = cityRepository.findAllByRegDateAfterOrderByRegDateDesc();
        //then
        assertThat(cities.size()).isEqualTo(2);
    }
}
