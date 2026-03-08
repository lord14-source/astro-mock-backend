package com.astromock.astromock.services;

import com.astromock.astromock.model.UpdateProfileDto;
import com.astromock.astromock.model.UserProfileDto;
import com.astromock.astromock.model.Users;
import com.astromock.astromock.repository.UserRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class ProfileService {

    private final UserRepository userRepository;

    public UserProfileDto getUserProfile(String email) {
        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return new UserProfileDto(user);
    }
    public UserProfileDto updateProfile(String email, UpdateProfileDto dto) {

        Users user = userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User not found"));

        // Update values
        user.setName(dto.getName());
        user.setPhnno(dto.getPhnno());

        // Save updated user
        Users savedUser = userRepository.save(user);

        // Return UPDATED values from saved entity (IMPORTANT)
        return new UserProfileDto(
                savedUser.getName(),
                savedUser.getEmail(),
                savedUser.getPhnno()
        );
    }
}