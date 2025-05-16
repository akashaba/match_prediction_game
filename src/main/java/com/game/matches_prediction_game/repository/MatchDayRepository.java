package com.game.matches_prediction_game.repository;

import com.game.matches_prediction_game.domain.MatchDay;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MatchDayRepository extends JpaRepository<MatchDay, Long> {
}
