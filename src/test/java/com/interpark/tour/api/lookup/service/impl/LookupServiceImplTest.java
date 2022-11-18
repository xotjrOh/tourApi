package com.interpark.tour.api.lookup.service.impl;

import com.interpark.tour.api.city.model.City;
import com.interpark.tour.api.city.repository.CityRepository;
import com.interpark.tour.api.lookup.model.Lookup;
import com.interpark.tour.api.lookup.model.LookupDTO;
import com.interpark.tour.api.lookup.repository.LookupRepository;
import com.interpark.tour.api.lookup.service.LookupService;
import com.interpark.tour.api.user.model.User;
import com.interpark.tour.api.user.repository.UserRepository;
import com.interpark.tour.common.exception.LookupException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("prod") // viewedCityInWeek 때문
@SpringBootTest
class LookupServiceImplTest {

    @Autowired
    LookupService lookupService;
    @Autowired
    LookupRepository lookupRepository;
    @Autowired
    UserRepository userRepository;
    @Autowired
    CityRepository cityRepository;

    Lookup lookupIns;

    // 안민우, 오태석에 해당하는 User / 도쿄, 서울에 해당하는 City 가 존재해야한다.
    @BeforeEach
    void given(){
        // cleanup
        lookupRepository.deleteAll();
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

        // given lookup
        lookupIns = lookupRepository.save(new Lookup(userRepository.findByName("오태석").get(),cityRepository.findByName("서울").get()));
        lookupRepository.save(new Lookup(userRepository.findByName("오태석").get(),cityRepository.findByName("도쿄").get()));
        lookupRepository.save(new Lookup(userRepository.findByName("안민우").get(),cityRepository.findByName("서울").get()));
    }

    @Test
    void lookupAll() {
        // when
        List<Lookup> lookups = lookupService.lookupAll();
        // then
        assertThat(lookups.size()).isEqualTo(3);
    }

    @Test
    void lookupById() {
        // when
        Lookup lookup = lookupService.lookupById(lookupIns.getId());
        // then
        assertThat(lookup.getCity().getName()).isEqualTo("서울");
    }

    @Test
    @DisplayName("존재하지않는 id로 조회할 경우")
    void lookupByWrongId() throws Exception {
        // when
        Long wrongId = lookupIns.getId()+3l;
        // then
        assertThatThrownBy(() -> lookupService.lookupById(wrongId))
                .isInstanceOf(LookupException.class)
                .hasMessage(wrongId+"에 해당하는 Lookup이 없습니다");
    }

    //나중에 덮어쓰기 구현
    @Test
    void lookupCreate() {
        // when
        LookupDTO lookupDTO = new LookupDTO(userRepository.findByName("오태석").get().getId(),cityRepository.findByName("제주도").get().getId());
        Lookup lookup = lookupService.lookupCreate(lookupDTO);
        // then
        assertThat(lookup.getCity().getName()).isEqualTo("제주도");
    }

    @Test
    @DisplayName("추가 조회로 시간만 변경")
    void lookupCreateDuplicate() {
        // when
        LookupDTO lookupDTO = new LookupDTO(userRepository.findByName("오태석").get().getId(),cityRepository.findByName("서울").get().getId());
        Lookup lookup = lookupService.lookupCreate(lookupDTO);
        // then
        assertThat(lookupIns.getRecentViewed()).isNotEqualTo(lookup.getRecentViewed());
    }

    @Test
    void lookupUpdate() {
        // when
        LookupDTO lookupDTO = new LookupDTO(userRepository.findByName("안민우").get().getId(),null);
        // then (null 값으로 덮어씌워지지 않는지 확인)
        assertThat(lookupService.lookupUpdate(lookupIns.getId(), lookupDTO).getCity().getName()).isEqualTo("서울");
    }

    @Test
    void lookupDelete() {
        // when
        boolean res = lookupService.lookupDelete(lookupIns.getId());
        // then
        assertThat(res).isEqualTo(true);
    }

    @Test
    void viewedCityInWeek() {
        // when
        List<String> viewedCities = lookupService.viewedCityInWeek(userRepository.findByName("오태석").get().getId());
        List<String> expectNames = Arrays.asList("도쿄","서울");
        // then
        assertThat(viewedCities).isEqualTo(expectNames);
    }

}