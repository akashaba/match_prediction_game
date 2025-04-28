package com.game.matches_prediction_game.service;

import com.game.matches_prediction_game.DTO.DivisionDTO;
import com.game.matches_prediction_game.domain.Division;
import com.game.matches_prediction_game.domain.Sport;
import com.game.matches_prediction_game.repository.DivisionRepository;
import com.game.matches_prediction_game.repository.SportRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DivisionService {
    private final DivisionRepository divisionRepository;
    private final SportRepository sportRepository;

    @Transactional
    public Division createDivision(DivisionDTO divisionDTO) {
        // Validate input
        if (divisionDTO.getDivisionName() == null || divisionDTO.getDivisionName().isEmpty()) {
            throw new IllegalArgumentException("Division name is required");
        }
        if (divisionDTO.getSportId() == null) {
            throw new IllegalArgumentException("Sport ID is required");
        }

        // Find the associated Sport
        Sport sport = sportRepository.findById(divisionDTO.getSportId())
                .orElseThrow(() -> new IllegalArgumentException("Sport not found with ID: " + divisionDTO.getSportId()));

        // Create Division entity
        Division division = new Division();
        division.setDivisionName(divisionDTO.getDivisionName());
        division.setSport(sport);

        // Handle leagues and teams if provided
        if (divisionDTO.getLeagues() != null) {
            divisionDTO.getLeagues().forEach(league -> league.setDivision(division));
            division.setLeagues(divisionDTO.getLeagues());
        }
        if (divisionDTO.getTeams() != null) {
            divisionDTO.getTeams().forEach(team -> team.setDivision(division));
            division.setTeams(divisionDTO.getTeams());
        }

        // Save to database
        return divisionRepository.save(division);
    }


    public List<Division> getAllDivisions() {
        return divisionRepository.findAll();
    }

    @Transactional
    public Division getDivisionById(Long id) {
        return divisionRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Division not found with ID: " + id));
    }
}
