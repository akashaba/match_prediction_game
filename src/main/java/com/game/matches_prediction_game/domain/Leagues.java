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
@Table(name = "LEAUGE")
public class Leagues {
    @Schema(description = "League ID", name = "id", type = "Long")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LEAGID", nullable = false)
    private Long id;
    @Schema(description = "League Name", name = "league_name", type = "String")
    @Column(name = "LEAGUE_NAME")
    private String league_name;
    @Schema(description = "League Code", name = "league_code", type = "String")
    @Column(name = "LEAGUE_CODE")
    private String league_code;
    @Schema(description = "Division ", name = "division_id", type = "Long")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "DIVSION_ID", referencedColumnName = "id")
    private Division division;
}
