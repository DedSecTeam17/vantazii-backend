package com.example.vantazii.messgingQueues.senders;

import org.junit.jupiter.api.Test;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.temporal.TemporalUnit;

import static org.junit.jupiter.api.Assertions.*;

class CustomerGamblingSenderTest {

    @Test
    void send() {
        LocalDateTime localDateTime = LocalDateTime.now();
        LocalDateTime localDateTime1 = LocalDateTime.now().plusMinutes(1);
//        from to
        Duration duration = Duration.between(localDateTime, localDateTime1);
        Long l = duration.toMillis();
        System.out.println(l.intValue());
        assertTrue(true);
    }
}