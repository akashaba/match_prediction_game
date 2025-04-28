package com.game.matches_prediction_game.DTO;

import com.game.matches_prediction_game.domain.Leagues;
import com.game.matches_prediction_game.domain.Teams;
import lombok.Data;

import java.util.List;

@Data
public class DivisionDTO {
    private String divisionName;
    private Long sportId; // Reference to Sport entity
    private List<Leagues> leagues; // Optional: Include if you want to create leagues with division
    private List<Teams> teams; // Optional: Include if you want to create teams with division
}
