package com.game.matches_prediction_game.repository;

import com.game.matches_prediction_game.domain.Users;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<Users, Long> {
}
