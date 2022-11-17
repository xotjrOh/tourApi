//package com.interpark.tour.api.lookup.svc.impl;
//
//import com.interpark.tour.api.city.model.City;
//import com.interpark.tour.api.lookup.model.Lookup;
//import com.interpark.tour.api.lookup.repo.LookupRepository;
//import com.interpark.tour.api.lookup.svc.LookupService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.ActiveProfiles;
//
//@ActiveProfiles("prod")
//@SpringBootTest
//class LookupServiceImplTest {
//
//    @Autowired
//    LookupService lookupService;
//    @Autowired
//    LookupRepository lookupRepository;
//
//    Lookup lookupIns;
//
//    @BeforeEach
//    void cleanup(){
//        lookupRepository.deleteAll();
//        // given
//        lookupIns = lookupRepository.save(new City("서울"));
//        lookupRepository.save(new City("도쿄"));
//        lookupRepository.save(new City("제주도"));
//    }
//
//    @Test
//    void lookupAll() {
//    }
//
//    @Test
//    void lookupById() {
//    }
//
//    @Test
//    void lookupCreate() {
//    }
//
//    @Test
//    void lookupUpdate() {
//    }
//
//    @Test
//    void lookupDelete() {
//    }
//}