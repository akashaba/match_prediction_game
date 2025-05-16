package com.game.matches_prediction_game.service;

import com.game.matches_prediction_game.DTO.UsersDTO;
import com.game.matches_prediction_game.domain.Users;
import com.game.matches_prediction_game.repository.UsersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsersService {
    private final UsersRepository usersRepository;

    @Transactional
    public Users createUser(UsersDTO usersDTO) {
        // Validate input
        if (usersDTO.getFirstName() == null || usersDTO.getFirstName().isEmpty()) {
            throw new IllegalArgumentException("First name is required");
        }
        if (usersDTO.getLastName() == null || usersDTO.getLastName().isEmpty()) {
            throw new IllegalArgumentException("Last name is required");
        }
        if (usersDTO.getUsername() == null || usersDTO.getUsername().isEmpty()) {
            throw new IllegalArgumentException("Username is required");
        }
        if (usersDTO.getRole() == null || usersDTO.getRole().isEmpty()) {
            throw new IllegalArgumentException("Role is required");
        }

        // Check if username is unique
        if (usersRepository.findByUsername(usersDTO.getUsername()).isPresent()) {
            throw new IllegalArgumentException("Username already exists");
        }

        // Create user
        Users user = new Users();
        user.setFirstName(usersDTO.getFirstName());
        user.setLastName(usersDTO.getLastName());
        user.setUsername(usersDTO.getUsername());
        user.setRole(usersDTO.getRole());
//        user.setPoints(usersDTO.getPoints() != null ? usersDTO.getPoints() : 0);

        return usersRepository.save(user);
    }

    @Transactional(readOnly = true)
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Users getUserById(Long id) {
        return usersRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + id));
    }
}
