package com.interpark.tour.api.tour.svc.impl;

import com.interpark.tour.api.city.repo.CityRepository;
import com.interpark.tour.api.tour.model.Tour;
import com.interpark.tour.api.tour.repo.TourRepository;
import com.interpark.tour.api.tour.svc.TourService;
import com.interpark.tour.api.user.repo.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;

@ActiveProfiles("prod")
@SpringBootTest
class TourServiceImplTest {

    @Autowired
    TourService tourService;
    @Autowired
    TourRepository tourRepository;
    @Autowired
    CityRepository cityRepository;
    @Autowired
    UserRepository userRepository;

    Tour tourIns;

    @BeforeEach
    void cleanup(){
        tourRepository.deleteAll();
        // given
        LocalDateTime localDateTime = LocalDateTime.now();
        tourIns = tourRepository.save(new Tour(
                cityRepository.findByName("서울").get(),
                cityRepository.findByName("도쿄").get(),
                localDateTime.minusDays(1),
                localDateTime,
                userRepository.findByName("안민우").get()
                ));
        tourRepository.save(new Tour(
                cityRepository.findByName("서울").get(),
                cityRepository.findByName("제주도").get(),
                localDateTime.minusDays(5),
                localDateTime.minusDays(2),
                userRepository.findByName("오태석").get()
        ));
        tourRepository.save(new Tour(
                cityRepository.findByName("서울").get(),
                cityRepository.findByName("도쿄").get(),
                localDateTime.minusDays(1),
                localDateTime,
                userRepository.findByName("이승현").get()
        ));
    }

    @Test
    void tourAll() {
    }

    @Test
    void tourById() {
    }

    @Test
    void tourCreate() {
    }

    @Test
    void tourUpdate() {
    }

    @Test
    void tourDelete() {
    }
}