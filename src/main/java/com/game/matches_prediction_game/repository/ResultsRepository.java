package com.game.matches_prediction_game.repository;

import com.game.matches_prediction_game.domain.Results;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ResultsRepository extends JpaRepository<Results, Long> {
    Optional<Results> findByMatchId(Long matchId);
}
