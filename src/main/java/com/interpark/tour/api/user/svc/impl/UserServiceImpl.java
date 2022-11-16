package com.interpark.tour.api.user.svc.impl;

import com.interpark.tour.api.user.model.User;
import com.interpark.tour.api.user.model.UserDto;
import com.interpark.tour.api.user.repo.UserRepository;
import com.interpark.tour.api.user.svc.UserService;
import com.interpark.tour.cmm.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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

        User user = userRepository.findById(userId).orElseThrow(() -> new UserException(userId + "에 해당하는 유저가 없습니다"));

        return user;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User userCreate(UserDto userDto) {

        if (userDto.getName()==null){
            throw new UserException("name과 nickName 이라는 키 값을 사용하여 json 데이터를 넘겨주세요");
        }
        if (userDto.getNickName()==null){
            throw new UserException("name과 nickName 이라는 키 값을 사용하여 json 데이터를 넘겨주세요");
        }

        User user = userRepository.save(new User(userDto.getName(), userDto.getNickName()));

        return user;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User userNickNameUpdate(Long userId, Map<String,String> nickNameMap) {

        User user = userRepository.findById(userId).orElseThrow(() -> new UserException(userId + "에 해당하는 유저가 없습니다"));
        String nickName = nickNameCheck(nickNameMap);
        user.setNickName(nickName);
        User updatedUser = userRepository.save(user);

        return updatedUser;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean userDelete(Long userId) {

        User user = userRepository.findById(userId).orElseThrow(() -> new UserException(userId + "에 해당하는 유저가 없습니다"));
        userRepository.delete(user);

        return true;
    }

    static String nickNameCheck(Map<String,String> nickNameMap){
        if (nickNameMap.get("nickName")==null) {
            throw new UserException("nickName 이라는 키 값을 사용하여 json 데이터를 넘겨주세요");
        }

        return nickNameMap.get("nickName");
    }

}
