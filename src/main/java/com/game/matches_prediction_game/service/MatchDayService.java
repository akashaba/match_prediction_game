package com.game.matches_prediction_game.service;

import com.game.matches_prediction_game.DTO.MatchDayDTO;
import com.game.matches_prediction_game.domain.MatchDay;
import com.game.matches_prediction_game.repository.MatchDayRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchDayService {
    private final MatchDayRepository matchDayRepository;

    @Transactional
    public MatchDay createMatchDay(MatchDayDTO matchDayDTO) {
        if (matchDayDTO.getMatchDay() == null) {
            throw new IllegalArgumentException("Match day number is required");
        }
        if (matchDayDTO.getMatchDayStart() == null || matchDayDTO.getMatchDayEnd() == null) {
            throw new IllegalArgumentException("Match day start and end times are required");
        }
        if (matchDayDTO.getMatchDayStart().isAfter(matchDayDTO.getMatchDayEnd())) {
            throw new IllegalArgumentException("Match day start must be before end");
        }

        MatchDay matchDay = new MatchDay();
        matchDay.setMatchDay(matchDayDTO.getMatchDay());
        matchDay.setMatchDayStart(matchDayDTO.getMatchDayStart());
        matchDay.setMatchDayEnd(matchDayDTO.getMatchDayEnd());

        return matchDayRepository.save(matchDay);
    }

    @Transactional(readOnly = true)
    public List<MatchDay> getAllMatchDays() {
        return matchDayRepository.findAll();
    }

    @Transactional(readOnly = true)
    public MatchDay getMatchDayById(Long id) {
        return matchDayRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Match day not found with ID: " + id));
    }
}
