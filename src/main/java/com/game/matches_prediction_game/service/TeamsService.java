package com.game.matches_prediction_game.service;

import com.game.matches_prediction_game.DTO.TeamsDTO;
import com.game.matches_prediction_game.domain.Division;
import com.game.matches_prediction_game.domain.Teams;
import com.game.matches_prediction_game.repository.DivisionRepository;
import com.game.matches_prediction_game.repository.TeamsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TeamsService {
    private final TeamsRepository teamsRepository;
    private final DivisionRepository divisionRepository;

    @Transactional
    public Teams createTeam(TeamsDTO teamsDTO) {
        if (teamsDTO.getTeamName() == null || teamsDTO.getTeamName().isEmpty()) {
            throw new IllegalArgumentException("Team name is required");
        }
        if (teamsDTO.getDivisionId() == null) {
            throw new IllegalArgumentException("Division ID is required");
        }

        Division division = divisionRepository.findById(teamsDTO.getDivisionId())
                .orElseThrow(() -> new IllegalArgumentException("Division not found with ID: " + teamsDTO.getDivisionId()));

        Teams team = new Teams();
        team.setTeamName(teamsDTO.getTeamName());
        team.setDivision(division);

        return teamsRepository.save(team);
    }

    @Transactional(readOnly = true)
    public List<Teams> getAllTeams() {
        return teamsRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Teams getTeamById(Long id) {
        return teamsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Team not found with ID: " + id));
    }
}
