package com.game.matches_prediction_game.repository;

import com.game.matches_prediction_game.domain.Matches;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MatchesRepository extends JpaRepository<Matches, Long> {
    @Query("SELECT m FROM Matches m WHERE m.matchDay.id = :matchDayId AND (m.homeTeam.id = :teamId OR m.awayTeam.id = :teamId)")
    List<Matches> findByMatchDayIdAndTeamId(Long matchDayId, Long teamId);
}
