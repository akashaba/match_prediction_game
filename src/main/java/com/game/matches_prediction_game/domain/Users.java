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
@Table(name = "USERS")
public class Users {
    @Schema(description = "User ID", name = "id", type = "Long")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "USERID", nullable = false)
    private Long id;
    @Schema(description = "First Name", name = "first_name", type = "String")
    @Column(name = "FIRST_NAME")
    private String first_name;
    @Schema(description = "Last Name", name = "last_name", type = "String")
    @Column(name = "LAST_NAME")
    private String last_name;
    @Schema(description = "Role", name = "role", type = "String")
    @Column(name = "ROLE")
    private String role;
    @Schema(description = "League", name = "league_id", type = "Long")
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "LEAGUE_ID", referencedColumnName = "id")
    private Leagues league_id;
    @Schema(description = "Points", name = "points", type = "Integer")
    @Column(name = "POINTS")
    private Integer points;
    @Schema(description = "Predictions", name = "predictions", type = "Predictions")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "user")
    private List<Predictions> predictions;
}
