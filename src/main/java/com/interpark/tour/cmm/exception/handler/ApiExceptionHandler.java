package com.interpark.tour.cmm.exception.handler;

import com.interpark.tour.cmm.exception.CityException;
import com.interpark.tour.cmm.exception.LookupException;
import com.interpark.tour.cmm.exception.TourException;
import com.interpark.tour.cmm.exception.UserException;
import com.interpark.tour.cmm.msg.ResultVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * author          : 오태석
 * date            : 2022/11/16
 * description     :
 * ===========================================================
 * DATE              AUTHOR                  NOTE
 * -----------------------------------------------------------
 * 2022/11/16        오태석               최초 생성
 */

@Slf4j
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(CityException.class)
    public ResponseEntity<ResultVo> cityExceptionHandler(final RuntimeException e){

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResultVo.builder()
                        .resultCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .resultMessage(e.getMessage())
                        .build());
    }

    @ExceptionHandler(LookupException.class)
    public ResponseEntity<ResultVo> lookupExceptionHandler(final RuntimeException e){

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResultVo.builder()
                        .resultCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .resultMessage(e.getMessage())
                        .build());
    }

    @ExceptionHandler(TourException.class)
    public ResponseEntity<ResultVo> tourExceptionHandler(final RuntimeException e){

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResultVo.builder()
                        .resultCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .resultMessage(e.getMessage())
                        .build());
    }

    @ExceptionHandler(UserException.class)
    public ResponseEntity<ResultVo> userExceptionHandler(final RuntimeException e){

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResultVo.builder()
                        .resultCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .resultMessage(e.getMessage())
                        .build());
    }

    @ExceptionHandler
    public ResponseEntity<ResultVo> unknownExceptionHandler(Exception e){

        // methodNotSupported 가능성이 높다
        log.error("[unknownExceptionHandler] : {}",e);

        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(ResultVo.builder()
                        .resultCode(String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()))
                        .resultMessage(e.getMessage())
                        .build());
    }
}
