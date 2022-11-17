package com.interpark.tour.temp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class SimpleTest {

    @Test
    @DisplayName("시간 비교")
    void findAll(){
        String name = null;

        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime past = localDateTime.minusMinutes(5);
        LocalDateTime future = localDateTime.plusMinutes(5);

        System.out.println(localDateTime);

        if (past.isBefore(past)){
            System.out.println("success");
        }


        // when
//        LocalDateTime date = LocalDateTime.now();
//        Map<String,String> nameMap = new HashMap<String, String>();
//        nameMap.put("named","도쿄"); // {name=도쿄}
//
//        System.out.println(nameMap);
//
//        System.out.println(nameMap.get("name")); // 도쿄
//        System.out.println(nameMap.get("named")); // null
//        System.out.println(nameMap.keySet()); //

//        UserDto userDto = new UserDto();
//
//        System.out.println(userDto);
//
//        System.out.println(userDto.getName());
//        System.out.println(userDto.getNickName());
//        if (nameMap.containsKey("name")) {
//
//            System.out.println(nameMap.get("name")); // null
//
//        }
        // then
    }


}
