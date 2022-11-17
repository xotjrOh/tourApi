package com.interpark.tour.temp;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

class SimpleTest {

    @Test
    @DisplayName("시간 비교")
    void findAll(){
        // 시간 비교
        String name = null;

        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime past = localDateTime.minusMinutes(5);
        LocalDateTime future = localDateTime.plusMinutes(5);

        System.out.println(localDateTime);

        if (past.isBefore(past)){
            System.out.println("success");
        }

        // 리스트 테스트
        List<Integer> ints = new ArrayList<>();
        ints.add(1);
        ints.add(2);
        List<Integer> ints2 = new ArrayList<>();
        ints.add(1);
        ints.add(2);
        ints.addAll(ints2);
        System.out.println(ints.subList(0,4));

    }


}
