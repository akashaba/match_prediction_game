package com.game.matches_prediction_game.service;

import com.game.matches_prediction_game.DTO.PredictionsDTO;
import com.game.matches_prediction_game.domain.Leagues;
import com.game.matches_prediction_game.domain.Matches;
import com.game.matches_prediction_game.domain.Predictions;
import com.game.matches_prediction_game.domain.Users;
import com.game.matches_prediction_game.repository.LeaguesRepository;
import com.game.matches_prediction_game.repository.MatchesRepository;
import com.game.matches_prediction_game.repository.PredictionsRepository;
import com.game.matches_prediction_game.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PredictionsService {
    private final PredictionsRepository predictionsRepository;
    private final MatchesRepository matchesRepository;
    private final UsersRepository usersRepository;
    private final LeaguesRepository leaguesRepository;

    @Transactional
    public Predictions createPrediction(PredictionsDTO predictionsDTO) {
        if (predictionsDTO.getMatchId() == null) {
            throw new IllegalArgumentException("Match ID is required");
        }
        if (predictionsDTO.getHome() == null || predictionsDTO.getAway() == null) {
            throw new IllegalArgumentException("Home and away scores are required");
        }
        if (predictionsDTO.getHome() < 0 || predictionsDTO.getAway() < 0) {
            throw new IllegalArgumentException("Scores cannot be negative");
        }
        if (predictionsDTO.getUsername() == null) {
            throw new IllegalArgumentException("Username is required");
        }
        if (predictionsDTO.getLeagueId() == null) {
            throw new IllegalArgumentException("League ID is required");
        }

        Matches match = matchesRepository.findById(predictionsDTO.getMatchId())
                .orElseThrow(() -> new IllegalArgumentException("Match not found with ID: " + predictionsDTO.getMatchId()));
        Users user = usersRepository.findByUsername(predictionsDTO.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found with username: " + predictionsDTO.getUsername()));
        Leagues league = leaguesRepository.findById(predictionsDTO.getLeagueId())
                .orElseThrow(() -> new IllegalArgumentException("League not found with ID: " + predictionsDTO.getLeagueId()));

        // Verify user is in the league
        if (!user.getLeagues().contains(league)) {
            throw new IllegalArgumentException("User is not a member of the specified league");
        }

        Predictions prediction = new Predictions();
        prediction.setMatch(match);
        prediction.setHome(predictionsDTO.getHome());
        prediction.setAway(predictionsDTO.getAway());
        prediction.setUser(user);
        prediction.setLeague(league);

        return predictionsRepository.save(prediction);
    }

    @Transactional(readOnly = true)
    public List<Predictions> getAllPredictions() {
        return predictionsRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Predictions getPredictionById(Long id) {
        return predictionsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Prediction not found with ID: " + id));
    }
}
