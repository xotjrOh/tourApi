package com.interpark.tour.api.lookup.svc.impl;

import com.interpark.tour.api.city.repo.CityRepository;
import com.interpark.tour.api.lookup.model.Lookup;
import com.interpark.tour.api.lookup.model.LookupDTO;
import com.interpark.tour.api.lookup.repo.LookupRepository;
import com.interpark.tour.api.lookup.svc.LookupService;
import com.interpark.tour.api.user.repo.UserRepository;
import com.interpark.tour.cmm.exception.LookupException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@ActiveProfiles("prod")
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

    @BeforeEach
    void cleanup(){
        lookupRepository.deleteAll();
        // given
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
        Assertions.assertThat(lookup.getCity().getName()).isEqualTo("서울");
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
        Assertions.assertThat(lookup.getCity().getName()).isEqualTo("제주도");
    }

    @Test
    @DisplayName("추가 조회로 시간만 변경")
    void lookupCreateDuplicate() {
        // when
        LookupDTO lookupDTO = new LookupDTO(userRepository.findByName("오태석").get().getId(),cityRepository.findByName("서울").get().getId());
        Lookup lookup = lookupService.lookupCreate(lookupDTO);
        // then
        Assertions.assertThat(lookupIns.getRecentViewed()).isNotEqualTo(lookup.getRecentViewed());
    }

    @Test
    void lookupUpdate() {
        // when
        LookupDTO lookupDTO = new LookupDTO(userRepository.findByName("안민우").get().getId(),null);
        // then (null 값으로 덮어씌워지지 않는지 확인)
        Assertions.assertThat(lookupService.lookupUpdate(lookupIns.getId(), lookupDTO).getCity().getName()).isEqualTo("서울");
    }

    @Test
    void lookupDelete() {
        // when
        boolean res = lookupService.lookupDelete(lookupIns.getId());
        // then
        Assertions.assertThat(res).isEqualTo(true);
    }
}