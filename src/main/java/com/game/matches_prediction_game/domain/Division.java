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
@Table(name = "Division")
public class Division {
    @Schema(description = "Division ID", name = "id", type = "Long")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DIVID", nullable = false)
    private Long id;
    @Schema(description = "Division Name", name = "division_name", type = "String")
    @Column(name = "DIVSION_NAME")
    private String division_name;
    @Schema(description = "Sports Name", name = "sport_name", type = "String")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "SPORT", referencedColumnName = "id")
    private Sport sport_name;
}
