package com.interpark.tour.api.city.ctr;

//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@Slf4j
@RequestMapping("/api/v1/city")
//@RequiredArgsConstructor
public class CityController {

    @GetMapping("/test")
    public ResponseEntity<String> test(){

        return ResponseEntity
                .status(HttpStatus.OK)
                .body("hihi");
    }

}
