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
@Table(name = "PREDICTIONS")
public class Predictions {
    @Schema(description = "Prediction ID", name = "id", type = "Long")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "PREDID", nullable = false)
    private Long id;

    @Schema(description = "Match", name = "match", type = "Matches")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MATCHID", referencedColumnName = "MATCHID", nullable = false)
    private Matches match;

    @Schema(description = "Home", name = "home", type = "Integer")
    @Column(name = "HOME", nullable = false)
    private Integer home;

    @Schema(description = "Away", name = "away", type = "Integer")
    @Column(name = "AWAY", nullable = false)
    private Integer away;

    @Schema(description = "User", name = "user", type = "Users")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERID", referencedColumnName = "USERID", nullable = false)
    @JsonIgnore
    private Users user;

    @Schema(description = "League", name = "league", type = "Leagues")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LEAGUEID", referencedColumnName = "LEAGID", nullable = false)
    private Leagues league;
}
