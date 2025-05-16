package com.game.matches_prediction_game.DTO;

import lombok.Data;

@Data
public class MatchesDTO {
    private Long homeTeamId;
    private Long awayTeamId;
    private Long matchDayId;
}
