package com.interpark.tour.api.user.svc;

import com.interpark.tour.api.user.model.User;

import java.util.List;

public interface UserService {

    List<User> userAll();

    User userById(Long userId);

    User userCreate(String name, String nickName);

    User userNickNameUpdate(Long userId, String nickName);

    boolean userDelete(Long userId);

}
