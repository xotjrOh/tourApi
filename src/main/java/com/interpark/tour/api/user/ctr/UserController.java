package com.interpark.tour.api.user.ctr;

import com.interpark.tour.api.user.model.UserDto;
import com.interpark.tour.api.user.svc.UserService;
import com.interpark.tour.cmm.msg.ResultVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/users")
@Slf4j
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    /**
     * User 전체 목록 조회
     * @return 전체 User 리스트
     */
    @GetMapping
    public ResponseEntity<ResultVo> userAll(){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResultVo.builder()
                        .resultCode(String.valueOf(HttpStatus.OK.value()))
                        .resultMessage("전체 user를 조회하였습니다")
                        .resultData(userService.userAll())
                        .build());
    }

    /**
     * User 단일 조회
     * @param userId 조회를 원하는 userId
     * @return User 정보
     */
    @GetMapping("/{userId}")
    public ResponseEntity<ResultVo> userById(@PathVariable Long userId){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResultVo.builder()
                        .resultCode(String.valueOf(HttpStatus.OK.value()))
                        .resultMessage("user를 조회하였습니다")
                        .resultData(userService.userById(userId))
                        .build());
    }

    /**
     * User 등록
     * @param userDto User 이름(name) , User 별명(nickName)
     * @return 생성된 User 정보
     */
    @PostMapping
    public ResponseEntity<ResultVo> userCreate(@RequestBody UserDto userDto){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResultVo.builder()
                        .resultCode(String.valueOf(HttpStatus.OK.value()))
                        .resultMessage("user를 등록하였습니다")
                        .resultData(userService.userCreate(userDto))
                        .build());
    }

    /**
     * User 수정
     * @param userId 변경을 원하는 User 의 Id
     * @param nickNameMap 변경될 user 별명 ( 무조건 key = "nickName" ) ex) {nickName=바보}
     * @return 변경된 User 정보
     */
    @PatchMapping("/{userId}")
    public ResponseEntity<ResultVo> userUpdate(@PathVariable Long userId,@RequestBody Map<String,String> nickNameMap){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResultVo.builder()
                        .resultCode(String.valueOf(HttpStatus.OK.value()))
                        .resultMessage("user를 수정하였습니다")
                        .resultData(userService.userNickNameUpdate(userId,nickNameMap))
                        .build());
    }

    /**
     * User 삭제
     * @param userId 삭제를 원하는 userId
     * @return User 삭제 성공 여부 (boolean)
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<ResultVo> userDelete(@PathVariable Long userId){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResultVo.builder()
                        .resultCode(String.valueOf(HttpStatus.OK.value()))
                        .resultMessage("user를 삭제하였습니다")
                        .resultData(userService.userDelete(userId))
                        .build());
    }


}
