package com.example.vantazii.core.util;

import lombok.SneakyThrows;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class TimerHelperTest {


    TimerHelper timerHelper;



    @SneakyThrows
    @Test
   public void convert() {
        timerHelper = new TimerHelper();
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime localDateTime1 = LocalDateTime.now().plusMinutes(1);
        assertEquals(timerHelper.convert(localDateTime,localDateTime1),60000);
    }
}