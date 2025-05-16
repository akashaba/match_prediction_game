package com.game.matches_prediction_game.service;

import com.game.matches_prediction_game.DTO.ResultsDTO;
import com.game.matches_prediction_game.domain.Leagues;
import com.game.matches_prediction_game.domain.Matches;
import com.game.matches_prediction_game.domain.Predictions;
import com.game.matches_prediction_game.domain.Results;
import com.game.matches_prediction_game.domain.UserLeaguePoints;
import com.game.matches_prediction_game.domain.Users;
import com.game.matches_prediction_game.repository.MatchesRepository;
import com.game.matches_prediction_game.repository.PredictionsRepository;
import com.game.matches_prediction_game.repository.ResultsRepository;
import com.game.matches_prediction_game.repository.UserLeaguePointsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ResultsService {
    private final ResultsRepository resultsRepository;
    private final MatchesRepository matchesRepository;
    private final PredictionsRepository predictionsRepository;
    private final UserLeaguePointsRepository userLeaguePointsRepository;

    @Transactional
    public Results createResult(ResultsDTO resultsDTO) {
        if (resultsDTO.getMatchId() == null) {
            throw new IllegalArgumentException("Match ID is required");
        }
        if (resultsDTO.getHome() == null || resultsDTO.getAway() == null) {
            throw new IllegalArgumentException("Home and away scores are required");
        }
        if (resultsDTO.getHome() < 0 || resultsDTO.getAway() < 0) {
            throw new IllegalArgumentException("Scores cannot be negative");
        }

        Matches match = matchesRepository.findById(resultsDTO.getMatchId())
                .orElseThrow(() -> new IllegalArgumentException("Match not found with ID: " + resultsDTO.getMatchId()));

        // Check if result already exists for the match
        if (resultsRepository.findByMatchId(resultsDTO.getMatchId()).isPresent()) {
            throw new IllegalArgumentException("Result already exists for match ID: " + resultsDTO.getMatchId());
        }

        Results result = new Results();
        result.setMatch(match);
        result.setHome(resultsDTO.getHome());
        result.setAway(resultsDTO.getAway());

        Results savedResult = resultsRepository.save(result);

        // Evaluate predictions and award points
        evaluatePredictions(match.getId());

        return savedResult;
    }

    @Transactional
    public void evaluatePredictions(Long matchId) {
        List<Predictions> predictions = predictionsRepository.findByMatchId(matchId);
        Results result = resultsRepository.findByMatchId(matchId)
                .orElseThrow(() -> new IllegalArgumentException("Result not found for match ID: " + matchId));

        for (Predictions prediction : predictions) {
            int points = calculatePoints(prediction, result);
            awardPoints(prediction.getUser(), prediction.getLeague(), points);
        }
    }

    private int calculatePoints(Predictions prediction, Results result) {
        int predHome = prediction.getHome();
        int predAway = prediction.getAway();
        int resHome = result.getHome();
        int resAway = result.getAway();

        // Exact match
        if (predHome == resHome && predAway == resAway) {
            return 5;
        }

        boolean isPredDraw = predHome == predAway;
        boolean isResDraw = resHome == resAway;
        boolean isPredHomeWin = predHome > predAway;
        boolean isPredAwayWin = predHome < predAway;
        boolean isResHomeWin = resHome > resAway;
        boolean isResAwayWin = resHome < resAway;

        // Correct draw (different score)
        if (isPredDraw && isResDraw) {
            return 3;
        }

        // Correct outcome (win/loss, different score)
        if ((isPredHomeWin && isResHomeWin) || (isPredAwayWin && isResAwayWin)) {
            return 3;
        }

        // Incorrect prediction
        return 0;
    }

    private void awardPoints(Users user, Leagues league, int points) {
        if (points == 0) {
            return;
        }

        UserLeaguePoints userLeaguePoints = userLeaguePointsRepository.findByUserIdAndLeagueId(user.getId(), league.getId())
                .orElseGet(() -> {
                    UserLeaguePoints newPoints = new UserLeaguePoints();
                    newPoints.setUser(user);
                    newPoints.setLeague(league);
                    newPoints.setPoints(0);
                    user.getLeaguePoints().add(newPoints);
                    return newPoints;
                });

        userLeaguePoints.setPoints(userLeaguePoints.getPoints() + points);
        userLeaguePointsRepository.save(userLeaguePoints);
    }

    @Transactional(readOnly = true)
    public List<Results> getAllResults() {
        return resultsRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Results getResultById(Long id) {
        return resultsRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Result not found with ID: " + id));
    }
}
