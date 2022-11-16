package com.interpark.tour;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

class SimpleTest {

    @Test
    @DisplayName("시간 비교")
    void findAll(){
        // when
//        LocalDateTime date = LocalDateTime.now();
        Map<String,String> nameMap = new HashMap<String, String>();
        nameMap.put("named","도쿄"); // {name=도쿄}

        System.out.println(nameMap);

        System.out.println(nameMap.get("name")); // 도쿄
        System.out.println(nameMap.get("named")); // null
        System.out.println(nameMap.keySet()); //

        if (nameMap.containsKey("name")) {

            System.out.println(nameMap.get("name")); // null

        }
        // then
    }


}
