package com.interpark.tour.api.tour.service.impl;

import com.interpark.tour.api.city.model.City;
import com.interpark.tour.api.city.repository.CityRepository;
import com.interpark.tour.api.tour.model.Tour;
import com.interpark.tour.api.tour.model.TourDTO;
import com.interpark.tour.api.tour.repository.TourRepository;
import com.interpark.tour.api.tour.service.TourService;
import com.interpark.tour.api.user.model.User;
import com.interpark.tour.api.user.repository.UserRepository;
import com.interpark.tour.common.exception.TourException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("prod") // getTravelingCities, getPlanCities 때문
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

    LocalDateTime localDateTime;
    Tour tourIns;

    @BeforeEach
    void given(){
        // cleanup
        tourRepository.deleteAll();
        userRepository.deleteAll();
        cityRepository.deleteAll();

        // given user
        userRepository.save(new User("오태석", "예삐공주"));
        userRepository.save(new User("안민우", "생명은 소중한거야"));
        userRepository.save(new User("이승현","깜둥쟁이"));

        // given city
        cityRepository.save(new City("서울"));
        cityRepository.save(new City("도쿄"));
        cityRepository.save(new City("제주도"));

        // given tour
        localDateTime = LocalDateTime.now();
        tourIns = tourRepository.save(new Tour(
                cityRepository.findByName("서울").get(),
                cityRepository.findByName("도쿄").get(),
                localDateTime.minusDays(2),
                localDateTime,
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
                localDateTime.minusDays(1),
                localDateTime.plusDays(5),
                userRepository.findByName("오태석").get()
        ));
    }

    @Test
    void tourAll() {
        // when
        List<Tour> tours = tourService.tourAll();
        // then
        assertThat(tours.size()).isEqualTo(4);
    }

    @Test
    void tourById() {
        // when
        Tour tour = tourService.tourById(tourIns.getId());
        // then
        assertThat(tour.getDepartures().getName()).isEqualTo("서울");
    }

    @Test
    @DisplayName("존재하지않는 id로 조회할 경우")
    void tourByWrongId() throws Exception {
        // when
        Long wrongId = tourIns.getId()+4l;
        // then
        assertThatThrownBy(() -> tourService.tourById(wrongId))
                .isInstanceOf(TourException.class)
                .hasMessage(wrongId+"에 해당하는 여행이 없습니다");
    }

    @Test
    void tourCreate() {
        // when
        TourDTO tourDTO = new TourDTO("도쿄","서울",localDateTime,localDateTime.plusDays(2),userRepository.findByName("오태석").get().getId());
        Tour tour = tourService.tourCreate(tourDTO);
        // then
        assertThat(tour.getDepartures().getName()).isEqualTo("도쿄");
    }

    @Test
    @DisplayName("과거로 여행")
    void tourCreateToPast() {
        // when
        TourDTO tourDTO = new TourDTO("도쿄","서울",localDateTime,localDateTime.minusDays(2),userRepository.findByName("오태석").get().getId());
        // then
        assertThatThrownBy(() -> tourService.tourCreate(tourDTO))
                .isInstanceOf(TourException.class)
                .hasMessage("여행 종료일은 미래만 허용");
    }

    @Test
    @DisplayName("update시 null값이 있을경우")
    void tourUpdate() {
        // when
        TourDTO tourDTO = new TourDTO("제주도",null,null,null, null);
        // then (null 값으로 덮어씌워지지 않는지 확인)
        assertThat(tourService.tourUpdate(tourIns.getId(), tourDTO).getArrivals().getName()).isEqualTo("도쿄");
    }

    @Test
    @DisplayName("update시 과거로 여행")
    void tourUpdateToPast() {
        // when
        TourDTO tourDTO = new TourDTO("제주도",null,localDateTime,localDateTime.minusDays(1), null);
        // then
        assertThatThrownBy(() -> tourService.tourUpdate(tourIns.getId(), tourDTO))
                .isInstanceOf(TourException.class)
                .hasMessage("여행 종료일은 미래만 허용");
    }

    @Test
    void tourDelete() {
        // when
        boolean res = tourService.tourDelete(tourIns.getId());
        // then
        assertThat(res).isEqualTo(true);
    }

    @Test
    void getTravelingCities() {
        // when
        List<String> travelingCities = tourService.getTravelingCities(userRepository.findByName("오태석").get().getId());
        // then
        assertThat(travelingCities.size()).isEqualTo(2);
    }

    @Test
    void getPlanCities() {
        // when
        List<String> planCities = tourService.getPlanCities(userRepository.findByName("이승현").get().getId());
        // then
        assertThat(planCities.size()).isEqualTo(1);
    }

}