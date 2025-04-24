package com.game.matches_prediction_game.repository;

import com.game.matches_prediction_game.domain.Leagues;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LeaguesRepository extends JpaRepository<Leagues, Long> {
}
