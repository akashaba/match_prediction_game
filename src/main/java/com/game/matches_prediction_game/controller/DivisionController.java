package com.game.matches_prediction_game.controller;

import com.game.matches_prediction_game.DTO.DivisionDTO;
import com.game.matches_prediction_game.domain.Division;
import com.game.matches_prediction_game.repository.DivisionRepository;
import com.game.matches_prediction_game.service.DivisionService;
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
public class DivisionController {

//    final private DivisionRepository divisionRepository;
    final private DivisionService divisionService;

    @PostMapping("/divisions")
    public ResponseEntity<Division> createDivision(@RequestBody DivisionDTO divisionDTO) {
        Division createdDivision = divisionService.createDivision(divisionDTO);
        return new ResponseEntity<>(createdDivision, HttpStatus.CREATED);
    }

    @GetMapping("/divisions")
    public ResponseEntity<List<Division>> getAllDivisions() {
        List<Division> divisions = divisionService.getAllDivisions();
        return ResponseEntity.ok(divisions);
    }

    @GetMapping("/divisions/{id}")
    public ResponseEntity<Division> getDivisionById(@PathVariable Long id) {
        Division division = divisionService.getDivisionById(id);
        return ResponseEntity.ok(division);
    }
}
