package com.example.vantazii.league;


import com.example.vantazii.core.config.FileManagmentConfig;
import com.example.vantazii.core.exception.ApiRequestException;
import com.example.vantazii.core.exception.CustomStatus.ApiExceptionType;
import com.example.vantazii.league.dto.LeagueDto;
import com.example.vantazii.league.dto.UpdateLeagueDto;
import com.example.vantazii.utils.filesManagments.FileManagmentService;
import lombok.AllArgsConstructor;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class LeagueService {


    private final FileManagmentService fileManagmentService;

    private final LeagueRepo leagueRepo;

    private FileManagmentConfig fileManagmentConfig;


    List<League> allLeagues() {
        return leagueRepo.findAll().stream().map(this::formLeagueWithFileUrl).collect(Collectors.toList());
    }

    League findById(UUID ID) {
        Optional<League> league = leagueRepo.findById(ID);
        if (league.isPresent()) {
            return league.get();
        } else {
            throw new ApiRequestException("League not found", ApiExceptionType.DEFAULT);
        }
    }

    League saveLeague(LeagueDto leagueDto) {
        League league = new League();
        league.setLeagueDescription(leagueDto.getLeagueDescription());
        league.setLeagueName(leagueDto.getLeagueName());
        uploadFileToEDM(leagueDto.getFile(), league);
        try {
            return formLeagueWithFileUrl(leagueRepo.save(league));
        } catch (Exception e) {
            throw new ApiRequestException("Error while saving the league", ApiExceptionType.DEFAULT);
        }
    }

    void deleteLeague(UUID id) {
        Optional<League> dbLeague = leagueRepo.findById(id);
        if (dbLeague.isPresent()) {
            try {
                fileManagmentService.deleteFile(dbLeague.get().getLeagueLogo());
                leagueRepo.deleteById(id);
            } catch (Exception e) {
                throw new ApiRequestException("Error while deleting the league", ApiExceptionType.DEFAULT);
            }
        } else {
            throw new ApiRequestException("League not found", ApiExceptionType.DEFAULT);
        }

    }

    League updateLeague(UpdateLeagueDto leagueDto, UUID leagueID) {
        Optional<League> dbLeague = leagueRepo.findById(leagueID);
        if (dbLeague.isPresent()) {
            League league = dbLeague.get();
            if (leagueDto.getLeagueDescription() != null) {
                league.setLeagueDescription(leagueDto.getLeagueDescription());
            }
            if (leagueDto.getLeagueName() != null) {
                league.setLeagueName(leagueDto.getLeagueName());
            }
            if (leagueDto.getFile() != null) {
                fileManagmentService.deleteFile(dbLeague.get().getLeagueLogo());
                uploadFileToEDM(leagueDto.getFile(), league);
            }

            return formLeagueWithFileUrl(leagueRepo.save(league));
        } else {
            throw new ApiRequestException("League not found", ApiExceptionType.DEFAULT);
        }
    }

    private void uploadFileToEDM(MultipartFile file, @NotNull League league) {
        String filePath = fileManagmentService.uploadFile(file);
        league.setLeagueLogo(filePath);
    }

    League formLeagueWithFileUrl(League league){
        league.setLeagueLogo(fileManagmentConfig.getPathToPreviewFiles()+league.getLeagueLogo());
        return  league;
    }


}
