package com.game.matches_prediction_game.service;

import com.game.matches_prediction_game.DTO.SportDTO;
import com.game.matches_prediction_game.domain.Sport;
import com.game.matches_prediction_game.repository.SportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SportService {
    private final SportRepository sportRepository;

    @Transactional
    public Sport createSport(SportDTO sportDTO) {
        // Validate input
        if (sportDTO.getSportName() == null || sportDTO.getSportName().isEmpty()) {
            throw new IllegalArgumentException("Sport name is required");
        }

        // Create Sport entity
        Sport sport = new Sport();
        sport.setSportName(sportDTO.getSportName());

        // Save to database
        return sportRepository.save(sport);
    }

    @Transactional(readOnly = true)
    public List<Sport> getAllSports() {
        return sportRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Sport getSportById(Long id) {
        return sportRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Sport not found with ID: " + id));
    }
}
