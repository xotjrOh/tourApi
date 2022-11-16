package com.interpark.tour.api.city.ctr;

import com.interpark.tour.api.city.svc.CityService;
import com.interpark.tour.cmm.msg.ResultVo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/cities")
@Slf4j
@RequiredArgsConstructor
public class CityController {

    private final CityService cityService;

    /**
     * City 전체 목록 조회
     * @return 전체 City 리스트
     */
    @GetMapping
    public ResponseEntity<ResultVo> cityAll(){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResultVo.builder()
                        .resultCode(String.valueOf(HttpStatus.OK.value()))
                        .resultMessage("전체 city를 조회하였습니다")
                        .resultData(cityService.cityAll())
                        .build());
    }

    /**
     * City 단일 조회
     * @param cityId 조회를 원하는 cityId
     * @return City 정보
     */
    @GetMapping("/{cityId}")
    public ResponseEntity<ResultVo> cityById(@PathVariable Long cityId){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResultVo.builder()
                        .resultCode(String.valueOf(HttpStatus.OK.value()))
                        .resultMessage("city를 조회하였습니다")
                        .resultData(cityService.cityById(cityId))
                        .build());
    }

    /**
     * City 등록
     * @param nameMap City 이름 ( 무조건 key = "name" ) ex) {name=도쿄}
     * @return 생성된 City 정보
     */
    @PostMapping
    public ResponseEntity<ResultVo> cityCreate(@RequestBody Map<String,String> nameMap){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResultVo.builder()
                        .resultCode(String.valueOf(HttpStatus.OK.value()))
                        .resultMessage("city를 등록하였습니다")
                        .resultData(cityService.cityCreate(nameMap))
                        .build());
    }

    /**
     * City 수정
     * @param cityId 변경을 원하는 City 의 Id
     * @param nameMap 변경될 City 이름 ( 무조건 key = "name" ) ex) {name=도쿄}
     * @return 변경된 City 정보
     */
    @PatchMapping("/{cityId}")
    public ResponseEntity<ResultVo> cityUpdate(@PathVariable Long cityId,@RequestBody Map<String,String> nameMap){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResultVo.builder()
                        .resultCode(String.valueOf(HttpStatus.OK.value()))
                        .resultMessage("city를 수정하였습니다")
                        .resultData(cityService.cityUpdate(cityId,nameMap))
                        .build());
    }

    /**
     * City 삭제
     * @param cityId 삭제를 원하는 cityId
     * @return City 삭제 성공 여부 (boolean)
     */
    @DeleteMapping("/{cityId}")
    public ResponseEntity<ResultVo> cityDelete(@PathVariable Long cityId){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResultVo.builder()
                        .resultCode(String.valueOf(HttpStatus.OK.value()))
                        .resultMessage("city를 삭제하였습니다")
                        .resultData(cityService.cityDelete(cityId))
                        .build());
    }

    /**
     * 특정 City 10개 조회
     * @param
     * @return 관심있을만한 City 10개 조회 (더 많을 수 있음)
     */
    @GetMapping("/list")
    public ResponseEntity<ResultVo> cityList(){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body(ResultVo.builder()
                        .resultCode(String.valueOf(HttpStatus.OK.value()))
                        .resultMessage("관심있을만한 city 리스트를 추출하였습니다")
                        .resultData(cityService.cityList())
                        .build());
    }

}
