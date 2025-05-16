package com.game.matches_prediction_game.controller;

import com.game.matches_prediction_game.DTO.ResultsDTO;
import com.game.matches_prediction_game.domain.Results;
import com.game.matches_prediction_game.service.ResultsService;
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
@RequestMapping("/v1/results")
@RequiredArgsConstructor
public class ResultsController {
    private final ResultsService resultsService;

    @PostMapping
    public ResponseEntity<Results> createResult(@RequestBody ResultsDTO resultsDTO) {
        Results createdResult = resultsService.createResult(resultsDTO);
        return new ResponseEntity<>(createdResult, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Results>> getAllResults() {
        List<Results> results = resultsService.getAllResults();
        return ResponseEntity.ok(results);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Results> getResultById(@PathVariable Long id) {
        Results result = resultsService.getResultById(id);
        return ResponseEntity.ok(result);
    }
}
