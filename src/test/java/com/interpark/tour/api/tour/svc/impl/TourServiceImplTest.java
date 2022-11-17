//package com.interpark.tour.api.tour.svc.impl;
//
//import com.interpark.tour.api.city.model.City;
//import com.interpark.tour.api.tour.model.Tour;
//import com.interpark.tour.api.tour.repo.TourRepository;
//import com.interpark.tour.api.tour.svc.TourService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//
//@ActiveProfiles("prod")
//@SpringBootTest
//class TourServiceImplTest {
//
//    @Autowired
//    TourService tourService;
//    @Autowired
//    TourRepository tourRepository;
//
//    Tour tourIns;
//
//    @BeforeEach
//    void cleanup(){
//        tourRepository.deleteAll();
//        // given
//        tourIns = tourRepository.save(new City("서울"));
//        tourRepository.save(new City("도쿄"));
//        tourRepository.save(new City("제주도"));
//    }
//
//    @Test
//    void tourAll() {
//    }
//
//    @Test
//    void tourById() {
//    }
//
//    @Test
//    void tourCreate() {
//    }
//
//    @Test
//    void tourUpdate() {
//    }
//
//    @Test
//    void tourDelete() {
//    }
//}