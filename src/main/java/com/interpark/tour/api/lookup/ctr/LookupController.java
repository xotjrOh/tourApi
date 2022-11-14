package com.interpark.tour.api.lookup.ctr;

//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@Slf4j
@RequestMapping("/api/v1/city")
//@RequiredArgsConstructor
public class LookupController {

//    @GetMapping("/test")
//    public ResponseEntity<String> test(){
//
//        return ResponseEntity
//                .status(HttpStatus.OK)
//                .body("hihi");
//    }

    @GetMapping("/test")
    public String test(){
        return "hi";
    }
}
