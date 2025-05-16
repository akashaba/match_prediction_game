package com.game.matches_prediction_game.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Table(name = "MATCHDAY")
public class MatchDay {
    @Schema(description = "Match Day ID", name = "id", type = "Long")
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MATDAYID", nullable = false)
    private Long id;

    @Schema(description = "Match Day", name = "match_day", type = "Integer")
    @Column(name = "MATCH_DAY", nullable = false)
    private Integer matchDay;

    @Schema(description = "Match Day Start", name = "match_day_start", type = "LocalDateTime")
    @Column(name = "MATCH_DAY_START", nullable = false)
    private LocalDateTime matchDayStart;

    @Schema(description = "Match Day End", name = "match_day_end", type = "LocalDateTime")
    @Column(name = "MATCH_DAY_END", nullable = false)
    private LocalDateTime matchDayEnd;

    @Schema(description = "Matches", name = "matches", type = "Matches")
    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "matchDay")
    @JsonIgnoreProperties("matchDay")
    private List<Matches> matches;
}
