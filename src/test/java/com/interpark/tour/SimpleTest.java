package com.interpark.tour;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

class SimpleTest {

    @Test
    @DisplayName("시간 비교")
    void findAll(){
        // when
        LocalDateTime date = LocalDateTime.now();
        System.out.println(date);

        // then
    }


}
