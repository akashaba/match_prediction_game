package com.game.matches_prediction_game.repository;

import com.game.matches_prediction_game.domain.Division;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DivisionRepository extends JpaRepository<Division, Long> {
}
