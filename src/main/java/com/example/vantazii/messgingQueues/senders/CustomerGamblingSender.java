package com.example.vantazii.messgingQueues.senders;


import com.example.vantazii.core.config.MessgingQueueConfig;
import com.example.vantazii.core.exception.ApiRequestException;
import com.example.vantazii.core.exception.CustomRunTime.OverDueMatchException;
import com.example.vantazii.core.exception.CustomStatus.ApiExceptionType;
import com.example.vantazii.core.util.TimerHelper;
import com.example.vantazii.footBallService.DTO.FixtureResponse.FixtureResponse;
import com.example.vantazii.footBallService.OpenFootballClient;
import com.example.vantazii.match.Match;
import com.example.vantazii.match.MatchService;
import com.example.vantazii.messgingQueues.dto.GambleMessage;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
@AllArgsConstructor
public class CustomerGamblingSender {
    private AmqpTemplate amqpTemplate;
    private MessgingQueueConfig messgingQueueConfig;

    private TimerHelper timerHelper;

    private MatchService matchService;

    private OpenFootballClient footballClient;

    public void send(GambleMessage gambleMessage) {
        System.out.println("Send msg = " + gambleMessage);

//
//        FixtureResponse fixtureResponse =
//        Match match = matchService.findOne(gambleMessage.getMatchID().toString());
//        LocalDateTime now = LocalDateTime.now();
//        try {
//            int delayFromNowTillGameStart = timerHelper.convert(now,match.getStartDate());
//            int delayFromGameStartTillGameEnd = timerHelper.convert(match.getStartDate(),match.getEndDate());
//            int totalDelay = delayFromNowTillGameStart + delayFromGameStartTillGameEnd;
//            System.out.println(totalDelay);
        amqpTemplate.convertAndSend(messgingQueueConfig.getQuqueExchange(), messgingQueueConfig.getQuqueRouting(), gambleMessage, message -> {
            message.getMessageProperties().setDelay(10);
            return message;
        });
//        } catch (OverDueMatchException e) {
//            throw new ApiRequestException(e.getMessage(),new Throwable().fillInStackTrace(), ApiExceptionType.DEFAULT);
//        }
    }
}
