package com.game.matches_prediction_game.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class MatchDayDTO {
    private Integer matchDay;
    private LocalDateTime matchDayStart;
    private LocalDateTime matchDayEnd;
}
