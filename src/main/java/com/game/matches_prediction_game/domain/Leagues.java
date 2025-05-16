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

import java.security.SecureRandom;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "LEAGUE")
public class Leagues {
    @Schema(description = "League ID", name = "id", type = "Long")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "LEAGID", nullable = false)
    private Long id;

    @Schema(description = "League Name", name = "league_name", type = "String")
    @Column(name = "LEAGUE_NAME", nullable = false)
    private String leagueName;

    @Schema(description = "League Code", name = "league_code", type = "String")
    @Column(name = "LEAGUE_CODE", nullable = false, unique = true)
    private String leagueCode;

    @Schema(description = "Division", name = "division_id", type = "Long")
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "DIVSION_ID", referencedColumnName = "DIVID")
    private Division division;

    // Generate a 6-character alphanumeric code
    public static String generateLeagueCode() {
        String characters = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        SecureRandom random = new SecureRandom();
        StringBuilder code = new StringBuilder(6);
        for (int i = 0; i < 6; i++) {
            code.append(characters.charAt(random.nextInt(characters.length())));
        }
        return code.toString();
    }
}
