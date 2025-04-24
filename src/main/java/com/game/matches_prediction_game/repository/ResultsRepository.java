package com.game.matches_prediction_game.repository;

import com.game.matches_prediction_game.domain.Results;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ResultsRepository extends JpaRepository<Results, Long> {
}
