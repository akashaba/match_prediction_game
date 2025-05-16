package com.game.matches_prediction_game.controller;

import com.game.matches_prediction_game.DTO.JoinLeagueDTO;
import com.game.matches_prediction_game.DTO.LeaguesDTO;
import com.game.matches_prediction_game.domain.Leagues;
import com.game.matches_prediction_game.service.LeaguesService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1/leagues")
@RequiredArgsConstructor
public class LeaguesController {
    private final LeaguesService leaguesService;

    @PostMapping
    public ResponseEntity<Leagues> createLeague(@RequestBody LeaguesDTO leaguesDTO) {
        Leagues createdLeague = leaguesService.createLeague(leaguesDTO);
        return new ResponseEntity<>(createdLeague, HttpStatus.CREATED);
    }

    @PostMapping("/join")
    public ResponseEntity<String> joinLeague(@RequestBody JoinLeagueDTO joinLeagueDTO) {
        leaguesService.joinLeague(joinLeagueDTO);
        return ResponseEntity.ok("User successfully joined the league");
    }

    @PostMapping("/{leagueId}/add-user")
    public ResponseEntity<String> addUserToLeague(
            @PathVariable Long leagueId,
            @RequestParam String username,
            @RequestParam String adminUsername) {
        leaguesService.addUserToLeague(leagueId, username, adminUsername);
        return ResponseEntity.ok("User successfully added to the league");
    }

    @GetMapping
    public ResponseEntity<List<Leagues>> getAllLeagues() {
        List<Leagues> leagues = leaguesService.getAllLeagues();
        return ResponseEntity.ok(leagues);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Leagues> getLeagueById(@PathVariable Long id) {
        Leagues league = leaguesService.getLeagueById(id);
        return ResponseEntity.ok(league);
    }
}
