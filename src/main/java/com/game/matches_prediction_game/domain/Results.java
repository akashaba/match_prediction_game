package com.game.matches_prediction_game.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "RESULTS")
public class Results {
    @Schema(description = "Result ID", name = "id", type = "Long")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RESID", nullable = false)
    private Long id;
    @Schema(description = "Match", name = "match", type = "Matches")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "matchId", referencedColumnName = "id")
    private Matches match;
    @Schema(description = "Home", name = "home", type = "Integer")
    @Column(name = "HOME")
    private Integer home;
    @Schema(description = "Away", name = "away", type = "Integer")
    @Column(name = "AWAY")
    private Integer away;
}
