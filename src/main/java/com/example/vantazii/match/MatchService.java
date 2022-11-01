package com.example.vantazii.match;


import com.example.vantazii.core.exception.ApiRequestException;
import com.example.vantazii.core.exception.CustomStatus.ApiExceptionType;
import com.example.vantazii.league.League;
import com.example.vantazii.league.LeagueRepo;
import com.example.vantazii.match.dto.CreateMatchDto;
import com.example.vantazii.match.dto.UpdateMatchDto;
import com.example.vantazii.team.Team;
import com.example.vantazii.team.TeamRepo;
import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class MatchService {


    private MatchRepo matchRepo;

    private LeagueRepo leagueRepo;

    private TeamRepo teamRepo;


    List<Match> findAll() {
        return Lists.newArrayList(matchRepo.findAll()) ;

    }

    Match findOne(String id) {
        Optional<Match> match = matchRepo.findById(UUID.fromString(id));
        if (match.isPresent()) return match.get();
        else throw new ApiRequestException("Match not found", ApiExceptionType.NOT_FOUND);

    }

    List<Match> allMatchesForLeague(String leagueId) {
        Optional<League> league = leagueRepo.findById(UUID.fromString(leagueId));
        if (league.isPresent()) {
            return matchRepo.findAllByLeagune(league.get());
        } else {
            throw new ApiRequestException("League not found", ApiExceptionType.NOT_FOUND);
        }
    }

    List<Match> allMatchesByHomeTeam(String teamId) {
        Optional<Team> team = teamRepo.findById(UUID.fromString(teamId));
        if (team.isPresent()) {
            return matchRepo.findAllByHomeTeam(team.get());
        } else {
            throw new ApiRequestException("Team not found", ApiExceptionType.NOT_FOUND);
        }
    }

    List<Match> allMatchesByAwayTeam(String teamId) {
        Optional<Team> team = teamRepo.findById(UUID.fromString(teamId));
        if (team.isPresent()) {
            return matchRepo.findAllByAwayTeam(team.get());
        } else {
            throw new ApiRequestException("Team not found", ApiExceptionType.NOT_FOUND);
        }
    }

    Match saveMatch(CreateMatchDto createMatchDto) {
        Match match = new Match();
        Optional<League> league = leagueRepo.findById(UUID.fromString(createMatchDto.getLeagueId()));
        Optional<Team> homeTeam = teamRepo.findById(UUID.fromString(createMatchDto.getHomeTeamId()));
        Optional<Team> awayTeam = teamRepo.findById(UUID.fromString(createMatchDto.getAwayTeamId()));
        if (league.isPresent() && homeTeam.isPresent() && awayTeam.isPresent()) {
            match.setLeagune(league.get());
            match.setHomeTeam(homeTeam.get());
            match.setAwayTeam(awayTeam.get());
            match.setResult("0:0");
            match.setStartDate(LocalDateTime.parse(createMatchDto.getStartTime()));
            match.setEndDate(LocalDateTime.parse(createMatchDto.getEndTime()));
            return matchRepo.save(match);
        } else {
            throw new ApiRequestException("teams or league not found", ApiExceptionType.NOT_FOUND);
        }
    }


    Match updateMatch(UpdateMatchDto updateMatchDto, String matchID) {
        Optional<Match> matchDb = matchRepo.findById(UUID.fromString(matchID));

        if (matchDb.isPresent()) {
            Match match = matchDb.get();

            if (updateMatchDto.getResult() != null) {
                match.setResult(updateMatchDto.getResult());
            }

            if (updateMatchDto.getStartTime() != null) {
                match.setStartDate(LocalDateTime.parse(updateMatchDto.getStartTime()));
            }

            if (updateMatchDto.getEndTime() != null) {
                match.setEndDate(LocalDateTime.parse(updateMatchDto.getEndTime()));
            }
            return matchRepo.save(match);

        } else {
            throw new ApiRequestException("Match not found", ApiExceptionType.NOT_FOUND);
        }
    }

    void deleteMatch(String matchId) {
        Optional<Match> match = matchRepo.findById(UUID.fromString(matchId));
        if (match.isPresent())
            matchRepo.delete(match.get());
        else
            throw new ApiRequestException("Match not found", new Throwable().fillInStackTrace(),ApiExceptionType.NOT_FOUND);
    }


}
