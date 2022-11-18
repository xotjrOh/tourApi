package com.interpark.tour.api.user.service.impl;

import com.interpark.tour.api.user.model.User;
import com.interpark.tour.api.user.model.UserDto;
import com.interpark.tour.api.user.repository.UserRepository;
import com.interpark.tour.api.user.service.UserService;
import com.interpark.tour.common.exception.UserException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

        String name = userDto.getName();
        String nickName = userDto.getNickName();
        if (name == null || nickName == null){
            throw new UserException("name과 nickName이라는 키값을 사용하여 json 데이터를 넘겨주세요");
        }
        // 별명 중복 체크
        List<String> nickNames = userAll().stream()
                                        .map(User::getNickName)
                                        .collect(Collectors.toList());
        if (nickNames.contains(nickName)){
            throw new UserException("이미 존재하는 별명입니다");
        }

        User user = userRepository.save(new User(name, nickName));

        return user;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public User userNickNameUpdate(Long userId, Map<String,String> nickNameMap) {

        User user = userRepository.findById(userId).orElseThrow(() -> new UserException(userId + "에 해당하는 유저가 없습니다"));
        String nickName = nickNameCheck(nickNameMap);
        // 별명 중복 체크
        List<String> nickNames = userAll().stream()
                .map(User::getNickName)
                .collect(Collectors.toList());
        if (nickNames.contains(nickName)){
            throw new UserException("이미 존재하는 별명입니다");
        }

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
            throw new UserException("nickName이라는 키값을 사용하여 json 데이터를 넘겨주세요");
        }

        return nickNameMap.get("nickName");
    }

}
