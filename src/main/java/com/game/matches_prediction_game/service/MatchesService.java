package com.game.matches_prediction_game.service;

import com.game.matches_prediction_game.DTO.MatchesDTO;
import com.game.matches_prediction_game.domain.MatchDay;
import com.game.matches_prediction_game.domain.Matches;
import com.game.matches_prediction_game.domain.Teams;
import com.game.matches_prediction_game.repository.MatchDayRepository;
import com.game.matches_prediction_game.repository.MatchesRepository;
import com.game.matches_prediction_game.repository.TeamsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchesService {
    private final MatchesRepository matchesRepository;
    private final TeamsRepository teamsRepository;
    private final MatchDayRepository matchDayRepository;

    @Transactional
    public Matches createMatch(MatchesDTO matchesDTO) {
        if (matchesDTO.getHomeTeamId() == null || matchesDTO.getAwayTeamId() == null) {
            throw new IllegalArgumentException("Home and away team IDs are required");
        }
        if (matchesDTO.getHomeTeamId().equals(matchesDTO.getAwayTeamId())) {
            throw new IllegalArgumentException("Home and away teams cannot be the same");
        }
        if (matchesDTO.getMatchDayId() == null) {
            throw new IllegalArgumentException("Match day ID is required");
        }

        Teams homeTeam = teamsRepository.findById(matchesDTO.getHomeTeamId())
                .orElseThrow(() -> new IllegalArgumentException("Home team not found with ID: " + matchesDTO.getHomeTeamId()));
        Teams awayTeam = teamsRepository.findById(matchesDTO.getAwayTeamId())
                .orElseThrow(() -> new IllegalArgumentException("Away team not found with ID: " + matchesDTO.getAwayTeamId()));
        MatchDay matchDay = matchDayRepository.findById(matchesDTO.getMatchDayId())
                .orElseThrow(() -> new IllegalArgumentException("Match day not found with ID: " + matchesDTO.getMatchDayId()));

        // Check if home or away team is already scheduled on this match day
        List<Matches> homeTeamMatches = matchesRepository.findByMatchDayIdAndTeamId(matchDay.getId(), homeTeam.getId());
        if (!homeTeamMatches.isEmpty()) {
            throw new IllegalArgumentException("Home team is already scheduled for a match on this match day");
        }
        List<Matches> awayTeamMatches = matchesRepository.findByMatchDayIdAndTeamId(matchDay.getId(), awayTeam.getId());
        if (!awayTeamMatches.isEmpty()) {
            throw new IllegalArgumentException("Away team is already scheduled for a match on this match day");
        }

        Matches match = new Matches();
        match.setHomeTeam(homeTeam);
        match.setAwayTeam(awayTeam);
        match.setMatchDay(matchDay);

        return matchesRepository.save(match);
    }

    @Transactional(readOnly = true)
    public List<Matches> getAllMatches() {
        return matchesRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Matches getMatchById(Long id) {
        return matchesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Match not found with ID: " + id));
    }
}
