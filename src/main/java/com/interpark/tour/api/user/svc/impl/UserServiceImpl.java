package com.interpark.tour.api.user.svc.impl;

import com.interpark.tour.api.user.model.User;
import com.interpark.tour.api.user.repo.UserRepository;
import com.interpark.tour.api.user.svc.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    @Transactional(readOnly = true)
    public List<User> userAll() {

        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public User userById(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException(userId + "에 해당하는 유저가 없습니다"));

        return user;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User userCreate(String name, String nickName) {

        User user = userRepository.save(new User(name, nickName));

        return user;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User userNickNameUpdate(Long userId, String nickName) {

        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException(userId + "에 해당하는 유저가 없습니다"));
        user.setNickName(nickName);
        User updatedUser = userRepository.save(user);

        return updatedUser;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean userDelete(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new NoSuchElementException(userId + "에 해당하는 유저가 없습니다"));
        userRepository.delete(user);

        return true;
    }

}
