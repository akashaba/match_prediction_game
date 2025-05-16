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
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
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
    @Column(name = "FIRST_NAME", nullable = false)
    private String firstName;

    @Schema(description = "Last Name", name = "last_name", type = "String")
    @Column(name = "LAST_NAME", nullable = false)
    private String lastName;

    @Schema(description = "User Name", name = "username", type = "String")
    @Column(name = "USERNAME", nullable = false, unique = true)
    private String username;

    @Schema(description = "Role", name = "role", type = "String")
    @Column(name = "ROLE", nullable = false)
    private String role;

    @Schema(description = "Leagues", name = "leagues", type = "List<Leagues>")
    @ManyToMany
    @JoinTable(
            name = "USER_LEAGUE",
            joinColumns = @JoinColumn(name = "USERID"),
            inverseJoinColumns = @JoinColumn(name = "LEAGID")
    )
//    @JsonIgnore
    private List<Leagues> leagues = new ArrayList<>();

    @Schema(description = "League Points", name = "leaguePoints", type = "List<UserLeaguePoints>")
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "user", orphanRemoval = true)
//    @JsonIgnore
    private List<UserLeaguePoints> leaguePoints = new ArrayList<>();
}
