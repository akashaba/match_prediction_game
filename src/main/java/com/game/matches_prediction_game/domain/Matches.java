package com.game.matches_prediction_game.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "MATCHES")
public class Matches {
    @Schema(description = "Match ID", name = "id", type = "Long")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MATCHID", nullable = false)
    private Long id;

    @Schema(description = "Home Team", name = "homeTeam", type = "Teams")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "HOME_TEAM_ID", referencedColumnName = "TEAMID", nullable = false)
    private Teams homeTeam;

    @Schema(description = "Away Team", name = "awayTeam", type = "Teams")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "AWAY_TEAM_ID", referencedColumnName = "TEAMID", nullable = false)
    private Teams awayTeam;

    @Schema(description = "Match Day", name = "matchDay", type = "MatchDay")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MATCHDAYID", referencedColumnName = "MATDAYID", nullable = false)
    @JsonIgnore
    private MatchDay matchDay;
}
