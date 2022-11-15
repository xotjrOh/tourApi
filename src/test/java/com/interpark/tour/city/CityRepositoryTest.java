package com.interpark.tour.city;

import com.interpark.tour.api.city.model.City;
import com.interpark.tour.api.city.repo.CityRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CityRepositoryTest {

    @Autowired
    CityRepository cityRepository;

    @BeforeEach
    void cleanup(){
        cityRepository.deleteAll();
        // given
        cityRepository.save(new City("서울"));
        cityRepository.save(new City("도쿄"));
    }

    @Test
    @DisplayName("findAll")
    void findAll(){
        // when
        List<City> cities = cityRepository.findAll();

        // then
        assertThat(cities.size()).isEqualTo(2);
    }


}
