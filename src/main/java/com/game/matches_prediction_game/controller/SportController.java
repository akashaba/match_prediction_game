package com.game.matches_prediction_game.controller;

import com.game.matches_prediction_game.domain.Sport;
import com.game.matches_prediction_game.repository.SportRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class SportController {

    @Autowired
    private SportRepository sportRepository;


    @GetMapping("/sports")
    public List<Sport> getSports(){
        List<Sport> sports = sportRepository.findAll();
        System.out.println(sports);
//        sports.forEach(sport -> System.out.println("Name: "+sport.getSportName()));
        return sports;
    }
}
