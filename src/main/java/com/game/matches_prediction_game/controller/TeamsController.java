package com.game.matches_prediction_game.controller;

import com.game.matches_prediction_game.DTO.TeamsDTO;
import com.game.matches_prediction_game.domain.Teams;
import com.game.matches_prediction_game.service.TeamsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/teams")
@RequiredArgsConstructor
public class TeamsController {
    private final TeamsService teamsService;

    @PostMapping
    public ResponseEntity<Teams> createTeam(@RequestBody TeamsDTO teamsDTO) {
        Teams createdTeam = teamsService.createTeam(teamsDTO);
        return new ResponseEntity<>(createdTeam, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Teams>> getAllTeams() {
        List<Teams> teams = teamsService.getAllTeams();
        return ResponseEntity.ok(teams);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Teams> getTeamById(@PathVariable Long id) {
        Teams team = teamsService.getTeamById(id);
        return ResponseEntity.ok(team);
    }
}
