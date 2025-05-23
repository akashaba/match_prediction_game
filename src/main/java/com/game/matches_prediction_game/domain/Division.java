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
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "DIVISION")
public class Division {
    @Schema(description = "Division ID", name = "id", type = "Long")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DIVID", nullable = false)
    private Long id;
    @Schema(description = "Division Name", name = "division_name", type = "String")
    @Column(name = "DIVSION_NAME")
    private String divisionName;
    @Schema(description = "Sports Name", name = "sport_id", type = "String")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "SPORT_ID", referencedColumnName = "SPTID")
    private Sport sport;
    @Schema(description = "Leagues", name = "league", type = "Leagues")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "division")
    @JsonIgnore
    private List<Leagues> leagues;
    @Schema(description = "Teams", name = "teams", type = "Teams")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "division")
    @JsonIgnore
    private List<Teams> teams;
}
