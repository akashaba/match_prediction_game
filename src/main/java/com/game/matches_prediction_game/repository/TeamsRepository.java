package com.game.matches_prediction_game.repository;

import com.game.matches_prediction_game.domain.Teams;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamsRepository extends JpaRepository<Teams, Long> {
}
