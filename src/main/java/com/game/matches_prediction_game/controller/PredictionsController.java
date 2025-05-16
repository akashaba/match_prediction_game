package com.game.matches_prediction_game.controller;

import com.game.matches_prediction_game.DTO.PredictionsDTO;
import com.game.matches_prediction_game.domain.Predictions;
import com.game.matches_prediction_game.service.PredictionsService;
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
@RequestMapping("/v1/predictions")
@RequiredArgsConstructor
public class PredictionsController {
    private final PredictionsService predictionsService;

    @PostMapping
    public ResponseEntity<Predictions> createPrediction(@RequestBody PredictionsDTO predictionsDTO) {
        Predictions createdPrediction = predictionsService.createPrediction(predictionsDTO);
        return new ResponseEntity<>(createdPrediction, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Predictions>> getAllPredictions() {
        List<Predictions> predictions = predictionsService.getAllPredictions();
        return ResponseEntity.ok(predictions);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Predictions> getPredictionById(@PathVariable Long id) {
        Predictions prediction = predictionsService.getPredictionById(id);
        return ResponseEntity.ok(prediction);
    }
}
