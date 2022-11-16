package com.interpark.tour.api.user.ctr;

import com.interpark.tour.api.user.model.User;
import com.interpark.tour.api.user.svc.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * User 전체 목록 조회
     * @return User 리스트
     */
    @GetMapping
    public ResponseEntity<List<User>> userAll(){

        List<User> users = userService.userAll();

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(users);
    }

    /**
     * User 단일 조회
     * @param userId 조회를 원하는 userId
     * @return User
     */
    @GetMapping("/{userId}")
    public ResponseEntity<User> userById(@PathVariable Long userId){

        User user = userService.userById(userId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(user);
    }

    /**
     * User 등록
     * @param name User 이름
     * @param nickName User 별명
     * @return 생성된 User 정보
     */
    @PostMapping
    public ResponseEntity<User> userCreate(String name, String nickName){

        User user = userService.userCreate(name, nickName);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(user);
    }

    /**
     * User 수정
     * @param userId 변경을 원하는 User 의 Id
     * @param nickName 변경될 user 별명
     * @return 변경된 User 정보
     */
    @PatchMapping("/{userId}")
    public ResponseEntity<User> userUpdate(@PathVariable Long userId, String nickName){

        User user = userService.userNickNameUpdate(userId,nickName);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(user);
    }

    /**
     * User 삭제
     * @param userId 삭제를 원하는 userId
     * @return 삭제된 User 정보
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<Boolean> userDelete(@PathVariable Long userId){

        boolean res = userService.userDelete(userId);

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(res);
    }


}
