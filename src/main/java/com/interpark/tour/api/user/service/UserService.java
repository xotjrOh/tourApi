package com.interpark.tour.api.user.service;

import com.interpark.tour.api.user.model.User;
import com.interpark.tour.api.user.model.UserDto;

import java.util.List;
import java.util.Map;

public interface UserService {

    List<User> userAll();

    User userById(Long userId);

    User userCreate(UserDto userDto);

    User userNickNameUpdate(Long userId, Map<String,String> nickNameMap);

    boolean userDelete(Long userId);

}
