package com.game.matches_prediction_game.controller;

import com.game.matches_prediction_game.DTO.MatchDayDTO;
import com.game.matches_prediction_game.domain.MatchDay;
import com.game.matches_prediction_game.service.MatchDayService;
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
@RequestMapping("/v1/matchdays")
@RequiredArgsConstructor
public class MatchDayController {
    private final MatchDayService matchDayService;

    @PostMapping
    public ResponseEntity<MatchDay> createMatchDay(@RequestBody MatchDayDTO matchDayDTO) {
        MatchDay createdMatchDay = matchDayService.createMatchDay(matchDayDTO);
        return new ResponseEntity<>(createdMatchDay, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<MatchDay>> getAllMatchDays() {
        List<MatchDay> matchDays = matchDayService.getAllMatchDays();
        return ResponseEntity.ok(matchDays);
    }

    @GetMapping("/{id}")
    public ResponseEntity<MatchDay> getMatchDayById(@PathVariable Long id) {
        MatchDay matchDay = matchDayService.getMatchDayById(id);
        return ResponseEntity.ok(matchDay);
    }
}
