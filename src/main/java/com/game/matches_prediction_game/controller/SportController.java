package com.game.matches_prediction_game.controller;

import com.game.matches_prediction_game.DTO.SportDTO;
import com.game.matches_prediction_game.domain.Sport;
import com.game.matches_prediction_game.repository.SportRepository;
import com.game.matches_prediction_game.service.SportService;
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
@RequestMapping("/v1")
@RequiredArgsConstructor
class SportController {

    final private SportRepository sportRepository;
    private final SportService sportService;



    @PostMapping("/sports")
    public ResponseEntity<Sport> createSport(@RequestBody SportDTO sportDTO) {
        Sport createdSport = sportService.createSport(sportDTO);
        return new ResponseEntity<>(createdSport, HttpStatus.CREATED);
    }

    @GetMapping("/sports")
    public ResponseEntity<List<Sport>> getAllSports() {
        List<Sport> sports = sportService.getAllSports();
        return ResponseEntity.ok(sports);
    }

    @GetMapping("/sports/{id}")
    public ResponseEntity<Sport> getSportById(@PathVariable Long id) {
        Sport sport = sportService.getSportById(id);
        return ResponseEntity.ok(sport);
    }
}
