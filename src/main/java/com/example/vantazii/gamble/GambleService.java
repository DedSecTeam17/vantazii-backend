package com.example.vantazii.gamble;


import com.example.vantazii.core.exception.ApiRequestException;
import com.example.vantazii.core.exception.CustomStatus.ApiExceptionType;
import com.example.vantazii.core.security.auth.facade.IAuthenticationFacade;
import com.example.vantazii.customer.Customer;
import com.example.vantazii.customer.CustomerRepo;
import com.example.vantazii.gamble.dto.CreateGambleDto;
import com.example.vantazii.gamble.dto.UpdateGambleDto;
import com.example.vantazii.match.Match;
import com.example.vantazii.match.MatchRepo;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class GambleService {

    private GambleRepo gambleRepo;

    private IAuthenticationFacade authenticationFacade;

    private CustomerRepo customerRepo;

    private MatchRepo matchRepo;

    public List<Gamble> findAllByCustomer() {
        return gambleRepo.findAllByCustomer(getCustomer());
    }


    public List<Gamble> findAllByMatch(String matchId) {
        return gambleRepo.findAllByMatch(getMatch(matchId));
    }

    public List<Gamble> all() {
        return gambleRepo.findAll();
    }
    public Gamble find(String id) {
        return getGamble(id);
    }

    public Gamble saveGmable(CreateGambleDto createGambleDto) {
        Gamble gamble = new Gamble();
        gamble.setCustomer(getCustomer());
        gamble.setMatch(getMatch(createGambleDto.getMatchId()));
        gamble.setWinner(false);
        gamble.setExpectedResult(createGambleDto.getExpectedResult());
        return gambleRepo.save(gamble);
    }

    public Gamble updateGmable(UpdateGambleDto updateGambleDto, String gambleId) {
        Gamble gamble = getGamble(gambleId);
        gamble.setWinner(updateGambleDto.isWinner());
        return gambleRepo.save(gamble);
    }

    public void deleteGamble(String gambleId) {
        Gamble gamble = this.getGamble(gambleId);
        gambleRepo.delete(gamble);
    }

    private Gamble getGamble(String gambleId) {
        Optional<Gamble> gamble = gambleRepo.findById(UUID.fromString(gambleId));
        if (gamble.isPresent())
            return gamble.get();
        else
            throw new ApiRequestException("Gamble not found", new Throwable().fillInStackTrace(), ApiExceptionType.DEFAULT);
    }

    private Match getMatch(String matchId) {
        Optional<Match> match = matchRepo.findById(UUID.fromString(matchId));
        if (match.isPresent())
            return match.get();
        else
            throw new ApiRequestException("Match not found", new Throwable().fillInStackTrace(), ApiExceptionType.DEFAULT);
    }
    private Customer getCustomer() {
        System.out.println(authenticationFacade.getAuthentication().getName());
        Optional<Customer> customer = customerRepo.findCustomerByPhoneNumber(authenticationFacade.getAuthentication().getName());

        if (customer.isPresent())
            return customer.get();
        else
            throw new ApiRequestException("Customer not found", new Throwable().fillInStackTrace(), ApiExceptionType.DEFAULT);
    }


}
