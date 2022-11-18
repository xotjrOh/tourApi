package com.interpark.tour;

import com.interpark.tour.api.city.model.City;
import com.interpark.tour.api.city.repository.CityRepository;
import com.interpark.tour.api.lookup.model.Lookup;
import com.interpark.tour.api.lookup.repository.LookupRepository;
import com.interpark.tour.api.tour.model.Tour;
import com.interpark.tour.api.tour.repository.TourRepository;
import com.interpark.tour.api.user.model.User;
import com.interpark.tour.api.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

@SpringBootTest
public class DummyCreate {

    @Autowired
    CityRepository cityRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    TourRepository tourRepository;
    @Autowired
    LookupRepository lookupRepository;

    @Test
    void createDummyData() {
        // 전체 삭제
        lookupRepository.deleteAll();
        tourRepository.deleteAll();
        userRepository.deleteAll();
        cityRepository.deleteAll();

        userDummy();
        cityDummy();
        tourDummy();
        lookupDummy();

    }

    void userDummy() {
        // 유저 생성
        userRepository.save(new User("주연규", "나홀로여행"));
        userRepository.save(new User("김한결", "북한빼고다가봄"));
        userRepository.save(new User("박건우","길잡이"));
        userRepository.save(new User("김지형","디지털노마드"));
        userRepository.save(new User("명중희","콜롬버스"));
        userRepository.save(new User("박하예린","떠도는예술가"));
        userRepository.save(new User("박민지","여행좋아"));
        userRepository.save(new User("정현지","타이완넘버원"));
        userRepository.save(new User("이민영","귀부인"));
        userRepository.save(new User("박현정","미국거주자"));
    }

    void cityDummy() {
        // 시티 생성
        cityRepository.save(new City("뉴욕"));
        cityRepository.save(new City("런던"));
        cityRepository.save(new City("홍콩"));
        cityRepository.save(new City("싱가포르"));
        cityRepository.save(new City("베이징"));
        cityRepository.save(new City("상하이"));
        cityRepository.save(new City("모스크바"));
        cityRepository.save(new City("마이애미"));
        cityRepository.save(new City("광저우"));
        cityRepository.save(new City("마닐라"));
        cityRepository.save(new City("비엔나"));
        cityRepository.save(new City("상파울로"));
        cityRepository.save(new City("토론토"));
        cityRepository.save(new City("암스테르담"));
        cityRepository.save(new City("싱가폴"));
    }

    void tourDummy() {
        LocalDateTime localDateTime = LocalDateTime.now();
        tourRepository.save(new Tour(
                cityRepository.findByName("뉴욕").get(),
                cityRepository.findByName("런던").get(),
                localDateTime.minusDays(20),
                localDateTime.minusDays(10),
                userRepository.findByName("박현정").get()
        ));
        tourRepository.save(new Tour(
                cityRepository.findByName("런던").get(),
                cityRepository.findByName("마닐라").get(),
                localDateTime.minusDays(10),
                localDateTime.plusDays(5),
                userRepository.findByName("박현정").get()
        ));
        tourRepository.save(new Tour(
                cityRepository.findByName("광저우").get(),
                cityRepository.findByName("홍콩").get(),
                localDateTime.plusDays(10),
                localDateTime.plusDays(25),
                userRepository.findByName("박하예린").get()
        ));
        tourRepository.save(new Tour(
                cityRepository.findByName("베이징").get(),
                cityRepository.findByName("상하이").get(),
                localDateTime.minusDays(5),
                localDateTime.plusDays(5),
                userRepository.findByName("이민영").get()
        ));
        tourRepository.save(new Tour(
                cityRepository.findByName("상하이").get(),
                cityRepository.findByName("마닐라").get(),
                localDateTime.plusDays(5),
                localDateTime.plusDays(10),
                userRepository.findByName("이민영").get()
        ));
        tourRepository.save(new Tour(
                cityRepository.findByName("마닐라").get(),
                cityRepository.findByName("뉴욕").get(),
                localDateTime.plusDays(10),
                localDateTime.plusDays(15),
                userRepository.findByName("이민영").get()
        ));
        tourRepository.save(new Tour(
                cityRepository.findByName("뉴욕").get(),
                cityRepository.findByName("암스테르담").get(),
                localDateTime.plusDays(15),
                localDateTime.plusDays(25),
                userRepository.findByName("이민영").get()
        ));
        tourRepository.save(new Tour(
                cityRepository.findByName("토론토").get(),
                cityRepository.findByName("모스크바").get(),
                localDateTime.minusDays(5),
                localDateTime.plusDays(5),
                userRepository.findByName("김한결").get()
        ));
    }

    void lookupDummy() {
        lookupRepository.save(new Lookup(userRepository.findByName("이민영").get(),cityRepository.findByName("토론토").get()));
        lookupRepository.save(new Lookup(userRepository.findByName("주연규").get(),cityRepository.findByName("런던").get()));
        lookupRepository.save(new Lookup(userRepository.findByName("주연규").get(),cityRepository.findByName("뉴욕").get()));
        lookupRepository.save(new Lookup(userRepository.findByName("주연규").get(),cityRepository.findByName("홍콩").get()));
        lookupRepository.save(new Lookup(userRepository.findByName("박하예린").get(),cityRepository.findByName("비엔나").get()));
        lookupRepository.save(new Lookup(userRepository.findByName("박현정").get(),cityRepository.findByName("마이애미").get()));
        lookupRepository.save(new Lookup(userRepository.findByName("박건우").get(),cityRepository.findByName("마닐라").get()));
        lookupRepository.save(new Lookup(userRepository.findByName("김지형").get(),cityRepository.findByName("상하이").get()));
        lookupRepository.save(new Lookup(userRepository.findByName("정현지").get(),cityRepository.findByName("싱가폴").get()));
    }

}
