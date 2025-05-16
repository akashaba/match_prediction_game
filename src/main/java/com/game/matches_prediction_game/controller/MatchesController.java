package com.game.matches_prediction_game.controller;

import com.game.matches_prediction_game.DTO.MatchesDTO;
import com.game.matches_prediction_game.domain.Matches;
import com.game.matches_prediction_game.service.MatchesService;
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
@RequestMapping("/v1/matches")
@RequiredArgsConstructor
public class MatchesController {
    private final MatchesService matchesService;

    @PostMapping
    public ResponseEntity<Matches> createMatch(@RequestBody MatchesDTO matchesDTO) {
        Matches createdMatch = matchesService.createMatch(matchesDTO);
        return new ResponseEntity<>(createdMatch, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Matches>> getAllMatches() {
        List<Matches> matches = matchesService.getAllMatches();
        return ResponseEntity.ok(matches);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Matches> getMatchById(@PathVariable Long id) {
        Matches match = matchesService.getMatchById(id);
        return ResponseEntity.ok(match);
    }
}
