package com.game.matches_prediction_game.repository;

import com.game.matches_prediction_game.domain.Matches;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MatchesRepository extends JpaRepository<Matches, Long> {
}
