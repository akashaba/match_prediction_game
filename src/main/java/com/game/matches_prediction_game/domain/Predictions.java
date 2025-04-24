package com.game.matches_prediction_game.domain;

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
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "matchId", referencedColumnName = "MATCHID")
    private Matches match;
    @Schema(description = "Home", name = "home", type = "Integer")
    @Column(name = "HOME")
    private Integer home;
    @Schema(description = "Away", name = "away", type = "Integer")
    @Column(name = "AWAY")
    private Integer away;
    @Schema(description = "User", name = "user", type = "Users")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    private Users user;
}
