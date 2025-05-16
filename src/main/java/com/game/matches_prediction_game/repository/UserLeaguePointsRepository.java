package com.game.matches_prediction_game.repository;

import com.game.matches_prediction_game.domain.UserLeaguePoints;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserLeaguePointsRepository extends JpaRepository<UserLeaguePoints, Long> {
    Optional<UserLeaguePoints> findByUserIdAndLeagueId(Long userId, Long leagueId);
}
