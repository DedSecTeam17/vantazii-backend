package com.example.vantazii.team;

import com.example.vantazii.core.config.FileManagmentConfig;
import com.example.vantazii.core.exception.ApiRequestException;
import com.example.vantazii.core.exception.CustomStatus.ApiExceptionType;
import com.example.vantazii.league.League;
import com.example.vantazii.league.LeagueRepo;
import com.example.vantazii.team.dto.CreateTeamDto;
import com.example.vantazii.team.dto.UpdateTeamDto;
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
public class TeamService {


    private TeamRepo teamRepo;

    private LeagueRepo leagueRepo;
    private FileManagmentService fileManagmentService;

    private FileManagmentConfig fileManagmentConfig;


    public List<Team> all(String leagueId) {
        Optional<League> league = leagueRepo.findById(UUID.fromString(leagueId));
        if (league.isPresent()) {
            return teamRepo.findAllByLeague(league.get()).stream().map(this::formTeamWithFileUrl).collect(Collectors.toList());
        }else {
            throw new ApiRequestException("League not found", ApiExceptionType.DEFAULT);

        }
    }

    public Team findOne(String id) {
        Optional<Team> team = teamRepo.findById(UUID.fromString(id));
        if (team.isPresent())
            return formTeamWithFileUrl(team.get());
        else
            throw new ApiRequestException("Team not found", ApiExceptionType.DEFAULT);
    }


    public Team createTeam(CreateTeamDto createTeamDto) {
        Optional<League> league = leagueRepo.findById(UUID.fromString(createTeamDto.getLeagueId()));
        if (league.isPresent()) {
            Team team = new Team();
            uploadFileToEDM(createTeamDto.getFile(), team);
            team.setTeamName(createTeamDto.getTeamName());
            team.setTeamDescription(createTeamDto.getTeamDescription());
            team.setLeague(league.get());
            return formTeamWithFileUrl(teamRepo.save(team));
        } else {
            throw new ApiRequestException("League not found", ApiExceptionType.DEFAULT);
        }
    }

    public Team updateTeam(UpdateTeamDto updateTeamDto, String teamID) {
        Optional<Team> dbTeam = teamRepo.findById(UUID.fromString(teamID));

        if (dbTeam.isPresent()) {
            Team team = dbTeam.get();
            if (updateTeamDto.getTeamDescription() != null)
                team.setTeamDescription(updateTeamDto.getTeamDescription());
            if (updateTeamDto.getTeamName() != null)
                team.setTeamName(updateTeamDto.getTeamName());
            if (updateTeamDto.getFile() != null) {
                fileManagmentService.deleteFile(team.getTeamLogo());
                uploadFileToEDM(updateTeamDto.getFile(), team);
            }
            return formTeamWithFileUrl(teamRepo.save(team));
        } else {
            throw new ApiRequestException("League not found", ApiExceptionType.DEFAULT);
        }
    }

    public void deleteTeam(String teamID) {
        Optional<Team> dbTeam = teamRepo.findById(UUID.fromString(teamID));
        if (dbTeam.isPresent()) {
            fileManagmentService.deleteFile(dbTeam.get().getTeamLogo());
            teamRepo.deleteById(UUID.fromString(teamID));
        } else {
            throw new ApiRequestException("League not found", ApiExceptionType.DEFAULT);
        }
    }
    private void uploadFileToEDM(MultipartFile file, @NotNull Team team) {
        String filePath = fileManagmentService.uploadFile(file);
        team.setTeamLogo(filePath);
    }
    Team formTeamWithFileUrl(Team team) {
        team.setTeamLogo(fileManagmentConfig.getPathToPreviewFiles() + team.getTeamLogo());
        return team;
    }


}
