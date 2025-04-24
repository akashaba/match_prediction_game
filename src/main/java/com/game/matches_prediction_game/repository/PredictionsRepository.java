package com.game.matches_prediction_game.repository;

import com.game.matches_prediction_game.domain.Predictions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PredictionsRepository extends JpaRepository<Predictions, Long> {
}
