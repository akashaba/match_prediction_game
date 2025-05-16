package com.game.matches_prediction_game.DTO;

import lombok.Data;

@Data
public class PredictionsDTO {
    private Long matchId;
    private Integer home;
    private Integer away;
    private String username;
    private Long leagueId;
}
