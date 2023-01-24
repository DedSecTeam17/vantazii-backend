package com.example.vantazii.footBallService;


import com.example.vantazii.core.config.OpenFeignConfig;
import com.example.vantazii.footBallService.DTO.FixtureResponse.FixtureResponse;
import com.example.vantazii.footBallService.DTO.FootBallDefaultResponse;
import com.example.vantazii.footBallService.DTO.LeagueResponse.LeagueResponse;
import feign.Headers;
import feign.Param;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient(value = "footballService", url = "https://api-football-v1.p.rapidapi.com/v3/", configuration = OpenFeignConfig.class)
public interface OpenFootballClient {
    @RequestMapping(method = RequestMethod.GET, value = "/leagues?country={country}")
    FootBallDefaultResponse<LeagueResponse> findAllLeaguesByCountry(@PathVariable String country);

    @RequestMapping(method = RequestMethod.GET, value = "/fixtures?league={league}&next={next}")
    FootBallDefaultResponse<FixtureResponse> fixturesForLeague(@PathVariable int league,@PathVariable int next);

    @RequestMapping(method = RequestMethod.GET, value = "/fixtures?id={id}")
    FootBallDefaultResponse<FixtureResponse> findFixtureById(@PathVariable String id);
}
