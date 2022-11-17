package com.interpark.tour.api.user.svc.impl;

import com.interpark.tour.api.user.model.User;
import com.interpark.tour.api.user.model.UserDto;
import com.interpark.tour.api.user.repo.UserRepository;
import com.interpark.tour.api.user.svc.UserService;
import com.interpark.tour.cmm.exception.UserException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
class UserServiceImplTest {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    User userIns;

    // 연관 관계가 생긴 이후에는 초기화에서 오류가 발생한다
    @BeforeEach
    void cleanup(){
        userRepository.deleteAll();
        // given
        userIns = userRepository.save(new User("오태석", "예삐공주"));
        userRepository.save(new User("안민우", "생명은 소중한거야"));
        userRepository.save(new User("이승현","깜둥쟁이"));
    }

    @Test
    void userAll() {
        // when
        List<User> users = userService.userAll();
        // then
        assertThat(users.size()).isEqualTo(3);
    }

    @Test
    void userById() {
        // when
        User user = userService.userById(userIns.getId());
        // then
        assertThat(user.getName()).isEqualTo("오태석");
    }

    @Test
    @DisplayName("존재하지않는 id로 조회할 경우")
    void userByWrongId() throws Exception {
        // when
        Long wrongId = userIns.getId()+3l;
        // then
        assertThatThrownBy(() -> userService.userById(wrongId))
                .isInstanceOf(UserException.class)
                .hasMessage(wrongId+"에 해당하는 유저가 없습니다");
    }

    @Test
    void userCreate() {
        // when
        UserDto userDto = new UserDto("방준성","미스터방씨");
        User user = userService.userCreate(userDto);
        // then
        assertThat(user.getName()).isEqualTo("방준성");
    }

    @Test
    @DisplayName("중복된 별명으로 생성할 경우")
    void userCreateDuplicateNickName() throws Exception {
        // when
        UserDto userDto = new UserDto("방준성","깜둥쟁이");
        // then
        assertThatThrownBy(() -> userService.userCreate(userDto))
                .isInstanceOf(UserException.class)
                .hasMessage("이미 존재하는 별명입니다");
    }

    @Test
    void userNickNameUpdate() {
        // when
        Map<String,String> nickNameMap = new HashMap<String, String>();
        nickNameMap.put("nickName","노는게제일좋아");
        User user = userService.userNickNameUpdate(userIns.getId(), nickNameMap);
        // then
        assertThat(user.getId()).isEqualTo(userIns.getId()); // 원객체와 수정된 객체 id가 같음을 확인
        assertThat(user.getNickName()).isEqualTo("노는게제일좋아");
    }

    @Test
    @DisplayName("중복된 별명으로 변경할 경우")
    void userNickNameUpdateDuplicate() throws Exception {
        // when
        Map<String,String> nickNameMap = new HashMap<String, String>();
        nickNameMap.put("nickName","깜둥쟁이");
        // then
        assertThatThrownBy(() -> userService.userNickNameUpdate(userIns.getId(), nickNameMap))
                .isInstanceOf(UserException.class)
                .hasMessage("이미 존재하는 별명입니다");
    }

    @Test
    @DisplayName("update시 key값이 틀릴경우")
    void userUpdateKeyError() throws Exception {
        // when
        Map<String,String> nickNameMap = new HashMap<String, String>();
        nickNameMap.put("nickname","노는게제일좋아");
        // then
        assertThatThrownBy(() -> userService.userNickNameUpdate(userIns.getId(), nickNameMap))
                .isInstanceOf(UserException.class)
                .hasMessage("nickName이라는 키값을 사용하여 json 데이터를 넘겨주세요");
    }

    @Test
    void userDelete() {
        // when
        boolean res = userService.userDelete(userIns.getId());
        // then
        assertThat(res).isEqualTo(true);
    }
}