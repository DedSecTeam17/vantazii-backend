package com.example.vantazii.core.util;

import com.example.vantazii.core.exception.CustomRunTime.OverDueMatchException;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class TimerHelper {

    public Integer convert(LocalDateTime from , LocalDateTime to) throws OverDueMatchException{
        if (from.isBefore(to)){
            Duration duration = Duration.between(from,to);
            return  (int) duration.toMillis();
        }else {
            throw new OverDueMatchException("Match is over due :(");
        }
    }
}
