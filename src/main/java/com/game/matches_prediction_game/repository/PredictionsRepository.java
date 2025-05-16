package com.game.matches_prediction_game.repository;

import com.game.matches_prediction_game.domain.Predictions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PredictionsRepository extends JpaRepository<Predictions, Long> {
    List<Predictions> findByMatchId(Long matchId);
}
