package com.interpark.tour.api.tour.ctr;

import com.interpark.tour.api.tour.model.TourDTO;
import com.interpark.tour.api.tour.svc.TourService;
import com.interpark.tour.cmm.msg.ResultVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/tours")
@Slf4j
@RequiredArgsConstructor
public class TourController {

    private final TourService tourService;

    /**
     * Tour 전체 목록 조회
     * @return 전체 Tour 리스트
     */
    @GetMapping
    public ResponseEntity<ResultVo> tourAll(){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResultVo.builder()
                        .resultCode(String.valueOf(HttpStatus.OK.value()))
                        .resultMessage("전체 tour를 조회하였습니다")
                        .resultData(tourService.tourAll())
                        .build());
    }

    /**
     * Tour 단일 조회
     * @param tourId 조회를 원하는 tourId
     * @return Tour 정보
     */
    @GetMapping("/{tourId}")
    public ResponseEntity<ResultVo> tourById(@PathVariable Long tourId){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResultVo.builder()
                        .resultCode(String.valueOf(HttpStatus.OK.value()))
                        .resultMessage("tour를 조회하였습니다")
                        .resultData(tourService.tourById(tourId))
                        .build());
    }

    /**
     * Tour 등록
     * @param tourDTO (departuresName, arrivalsName, startDate, endDate, userId)
     * @return 생성된 Tour 정보
     */
    @PostMapping
    public ResponseEntity<ResultVo> tourCreate(@RequestBody TourDTO tourDTO){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResultVo.builder()
                        .resultCode(String.valueOf(HttpStatus.OK.value()))
                        .resultMessage("tour를 등록하였습니다")
                        .resultData(tourService.tourCreate(tourDTO))
                        .build());
    }

    /**
     * Tour 수정
     * @param tourId 변경을 원하는 Tour 의 Id
     * @param tourDTO (departuresName, arrivalsName, startDate, endDate, userId)
     * @return 변경된 Tour 정보
     */
    @PatchMapping("/{tourId}")
    public ResponseEntity<ResultVo> tourUpdate(@PathVariable Long tourId, @RequestBody TourDTO tourDTO){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResultVo.builder()
                        .resultCode(String.valueOf(HttpStatus.OK.value()))
                        .resultMessage("tour를 수정하였습니다")
                        .resultData(tourService.tourUpdate(tourId,tourDTO))
                        .build());
    }

    /**
     * Tour 삭제
     * @param tourId 삭제를 원하는 tourId
     * @return Tour 삭제 성공 여부 (boolean)
     */
    @DeleteMapping("/{tourId}")
    public ResponseEntity<ResultVo> tourDelete(@PathVariable Long tourId){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResultVo.builder()
                        .resultCode(String.valueOf(HttpStatus.OK.value()))
                        .resultMessage("tour를 삭제하였습니다")
                        .resultData(tourService.tourDelete(tourId))
                        .build());
    }

}
