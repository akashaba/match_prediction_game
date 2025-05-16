package com.game.matches_prediction_game.service;

import com.game.matches_prediction_game.DTO.JoinLeagueDTO;
import com.game.matches_prediction_game.DTO.LeaguesDTO;
import com.game.matches_prediction_game.domain.Division;
import com.game.matches_prediction_game.domain.Leagues;
import com.game.matches_prediction_game.domain.Users;
import com.game.matches_prediction_game.repository.DivisionRepository;
import com.game.matches_prediction_game.repository.LeaguesRepository;
import com.game.matches_prediction_game.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LeaguesService {
    private final LeaguesRepository leaguesRepository;
    private final DivisionRepository divisionRepository;
    private final UsersRepository usersRepository;

    @Transactional
    public Leagues createLeague(LeaguesDTO leaguesDTO) {
        // Validate input
        if (leaguesDTO.getLeagueName() == null || leaguesDTO.getLeagueName().isEmpty()) {
            throw new IllegalArgumentException("League name is required");
        }
        if (leaguesDTO.getDivisionId() == null) {
            throw new IllegalArgumentException("Division ID is required");
        }

        // Find division
        Division division = divisionRepository.findById(leaguesDTO.getDivisionId())
                .orElseThrow(() -> new IllegalArgumentException("Division not found with ID: " + leaguesDTO.getDivisionId()));

        // Generate unique league code
        String leagueCode;
        do {
            leagueCode = Leagues.generateLeagueCode();
        } while (leaguesRepository.findByLeagueCode(leagueCode).isPresent());

        // Create league
        Leagues league = new Leagues();
        league.setLeagueName(leaguesDTO.getLeagueName());
        league.setLeagueCode(leagueCode);
        league.setDivision(division);

        return leaguesRepository.save(league);
    }

    @Transactional
    public void joinLeague(JoinLeagueDTO joinLeagueDTO) {
        // Find league by code
        Leagues league = leaguesRepository.findByLeagueCode(joinLeagueDTO.getLeagueCode())
                .orElseThrow(() -> new IllegalArgumentException("Invalid league code: " + joinLeagueDTO.getLeagueCode()));

        // Find user by username
        Users user = usersRepository.findByUsername(joinLeagueDTO.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("User not found with username: " + joinLeagueDTO.getUsername()));

        // Check if user is already in the league
        if (user.getLeagues().contains(league)) {
            throw new IllegalArgumentException("User is already in the league");
        }

        // Add user to league
        user.getLeagues().add(league);
        usersRepository.save(user);
    }

    @Transactional
    public void addUserToLeague(Long leagueId, String username, String adminUsername) {
        // Verify admin
        Users admin = usersRepository.findByUsername(adminUsername)
                .orElseThrow(() -> new IllegalArgumentException("Admin not found with username: " + adminUsername));
        if (!"ADMIN".equalsIgnoreCase(admin.getRole())) {
            throw new IllegalArgumentException("Only admins can add users to leagues");
        }

        // Find league
        Leagues league = leaguesRepository.findById(leagueId)
                .orElseThrow(() -> new IllegalArgumentException("League not found with ID: " + leagueId));

        // Find user
        Users user = usersRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found with username: " + username));

        // Check if user is already in the league
        if (user.getLeagues().contains(league)) {
            throw new IllegalArgumentException("User is already in the league");
        }

        // Add user to league
        user.getLeagues().add(league);
        usersRepository.save(user);
    }

    @Transactional(readOnly = true)
    public List<Leagues> getAllLeagues() {
        return leaguesRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Leagues getLeagueById(Long id) {
        return leaguesRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("League not found with ID: " + id));
    }
}
