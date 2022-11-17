package com.interpark.tour.api.lookup.ctr;

import com.interpark.tour.api.lookup.model.LookupDTO;
import com.interpark.tour.api.lookup.svc.LookupService;
import com.interpark.tour.cmm.msg.ResultVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/lookups")
@Slf4j
@RequiredArgsConstructor
public class LookupController {

    private final LookupService lookupService;

    /**
     * Lookup 전체 목록 조회
     * @return 전체 Lookup 리스트
     */
    @GetMapping
    public ResponseEntity<ResultVo> lookupAll(){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResultVo.builder()
                        .resultCode(String.valueOf(HttpStatus.OK.value()))
                        .resultMessage("전체 lookup을 조회하였습니다")
                        .resultData(lookupService.lookupAll())
                        .build());
    }

    /**
     * Lookup 단일 조회
     * @param lookupId 조회를 원하는 lookupId
     * @return Lookup 정보
     */
    @GetMapping("/{lookupId}")
    public ResponseEntity<ResultVo> lookupById(@PathVariable Long lookupId){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResultVo.builder()
                        .resultCode(String.valueOf(HttpStatus.OK.value()))
                        .resultMessage("lookup을 조회하였습니다")
                        .resultData(lookupService.lookupById(lookupId))
                        .build());
    }

    /**
     * Lookup 등록 (user,city 모두 중복시 시간만 갱신)
     * @param lookupDTO (userId, cityId)
     * @return 생성(또는 변경)된 Lookup 정보
     */
    @PostMapping
    public ResponseEntity<ResultVo> lookupCreate(@RequestBody LookupDTO lookupDTO){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResultVo.builder()
                        .resultCode(String.valueOf(HttpStatus.OK.value()))
                        .resultMessage("lookup을 등록(또는 변경)하였습니다")
                        .resultData(lookupService.lookupCreate(lookupDTO))
                        .build());
    }

    /**
     * Lookup 수정
     * @param lookupId 변경을 원하는 Lookup 의 Id
     * @param lookupDTO (userId, cityId)
     * @return 변경된 Lookup 정보
     */
    @PatchMapping("/{lookupId}")
    public ResponseEntity<ResultVo> lookupUpdate(@PathVariable Long lookupId, @RequestBody LookupDTO lookupDTO){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResultVo.builder()
                        .resultCode(String.valueOf(HttpStatus.OK.value()))
                        .resultMessage("lookup을 수정하였습니다")
                        .resultData(lookupService.lookupUpdate(lookupId,lookupDTO))
                        .build());
    }

    /**
     * Lookup 삭제
     * @param lookupId 삭제를 원하는 lookupId
     * @return Lookup 삭제 성공 여부 (boolean)
     */
    @DeleteMapping("/{lookupId}")
    public ResponseEntity<ResultVo> lookupDelete(@PathVariable Long lookupId){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResultVo.builder()
                        .resultCode(String.valueOf(HttpStatus.OK.value()))
                        .resultMessage("lookup을 삭제하였습니다")
                        .resultData(lookupService.lookupDelete(lookupId))
                        .build());
    }

}
