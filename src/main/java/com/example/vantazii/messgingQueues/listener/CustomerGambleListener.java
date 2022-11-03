package com.example.vantazii.messgingQueues.listener;

import com.example.vantazii.gamble.Gamble;
import com.example.vantazii.gamble.GambleService;
import com.example.vantazii.gamble.dto.UpdateGambleDto;
import com.example.vantazii.match.Match;
import com.example.vantazii.match.MatchService;
import com.example.vantazii.messgingQueues.dto.GambleMessage;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;
import org.springframework.amqp.support.converter.MessageConverter;


@AllArgsConstructor
public class CustomerGambleListener implements MessageListener {

    private MessageConverter messageConverter;

    private GambleService gambleService;

    private MatchService matchService;
    @Override
    public void onMessage(Message message) {
        GambleMessage gambleMessage = (GambleMessage) messageConverter.fromMessage(message);
        System.out.println("Recived : --->"+gambleMessage);
        Match match = matchService.findOne(gambleMessage.getMatchID().toString());
        boolean isWinner  = match.getResult().equals(gambleMessage.getExpectedResult());
        UpdateGambleDto updateGambleDto = new UpdateGambleDto();
        updateGambleDto.setWinner(isWinner);
        Gamble updatedGample = gambleService.updateGmable(updateGambleDto,gambleMessage.getGampleID().toString());
        System.out.println("Recived : --->");
    }

}
