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
@Table(name = "TEAM")
public class Teams {
    @Schema(description = "Team ID", name = "id", type = "Long")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEAMID", nullable = false)
    private Long id;
    @Schema(description = "Team Name", name = "team_name", type = "String")
    @Column(name = "TEAM_NAME")
    private String team_name;
    @Schema(description = "Division ", name = "division_id", type = "Long")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DIVSION_ID", referencedColumnName = "id")
    private Division division;
}
