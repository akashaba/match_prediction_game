package com.game.matches_prediction_game.repository;

import com.game.matches_prediction_game.domain.MatchDay;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchDayRepository extends JpaRepository<MatchDay, Long> {
}
