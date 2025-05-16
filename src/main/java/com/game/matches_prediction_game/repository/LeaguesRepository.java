package com.game.matches_prediction_game.repository;

import com.game.matches_prediction_game.domain.Leagues;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LeaguesRepository extends JpaRepository<Leagues, Long> {
    Optional<Leagues> findByLeagueCode(String leagueCode);
}
